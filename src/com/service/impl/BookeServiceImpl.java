package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.Constants;
import com.dao.BookDao;
import com.dao.ChapterPaylogDao;
import com.dao.DaoFactory;
import com.dao.UserInfoDao;
import com.service.BookService;
import com.service.ChapterPaylogService;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

public class BookeServiceImpl implements BookService {
	private static BookDao bookDao = new BookDao();
	private static UserInfoDao userInfoDao = new UserInfoDao();
	private static ChapterPaylogService chapterPaylogService = new ChapterPaylogServiceImpl();
	private static ChapterPaylogDao chapterPaylogDao = new ChapterPaylogDao();
	private static Logger logger = Logger.getLogger(BookeServiceImpl.class);
	private IRedisDao redisDao = DaoFactory.getRedisDao();

	/**
	 * 获取图书
	 * @param appId
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getBookData(String appId,int pageNo) throws Exception {
		//获取图书列表
		List<Map<String,Object>> bookList = bookDao.getBookData(appId,pageNo);

		//处理数据
		for(Map<String,Object> map : bookList){
			String userId = map.get("authorId").toString();
			map.put("userInfo",userInfoDao.getUserInfoByUserId(Long.parseLong(userId)));

			//处理字数
			double wordCount = Double.parseDouble(map.get("wordCount").toString());
			double d = 10000d;
			if(wordCount>d){
				BigDecimal b   =   new   BigDecimal(wordCount/d);
				double f1  =  b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
				map.put("wordCount",String.valueOf(f1)+"W");
				map.put("wordPrice"," 0.03钻/千字");
			}else{
				map.put("wordCount",String.valueOf(wordCount));
				map.put("wordPrice"," 0.03钻/千字");
			}
		}


		Map<String,Object> bookMap = new HashMap<String, Object>();
		bookMap.put("pageNo",pageNo);
		bookMap.put("pageSize", Constants.PAGE_SIZE);
		bookMap.put("bookList",bookList);
		return bookMap;
	}


	/**
	 * 获取所有目录
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getBookChapter(String bookId,String userId) throws Exception {
		List<Map<String,Object>> bookChapterList = new ArrayList<Map<String, Object>>();
		Set<Integer> strSet = new HashSet<Integer>();
		JSONArray jsonArray = new JSONArray();

		List<Map<String,Object>>  chapterList = bookDao.getBookChapter(bookId);

		//查询用户是否购买过此章节
		List<Map<String,Object>> chapterPaylogList =  chapterPaylogDao.getBookData(bookId,userId);

		if(chapterPaylogList!=null&&chapterPaylogList.size()>0){
			//将查询到的购买的记录保存到set
			for(Map payLogMap:chapterPaylogList ){
				int startChapterId = Integer.parseInt(String.valueOf(payLogMap.get("startChapterId")));
				int endChapterId = Integer.parseInt(String.valueOf(payLogMap.get("endChapterId")));
				if(endChapterId>startChapterId){
					for(int n = startChapterId;n<=endChapterId;n++){
						strSet.add(n);
					}
				}
				if(endChapterId==startChapterId){
					strSet.add(endChapterId);
				}
			}
		}



		if(chapterList.size()>0&&!chapterList.isEmpty()){
			for(Map<String,Object> map :chapterList){
				jsonArray = JSONArray.parseArray(String.valueOf(map.get("bookChapterList")));
				for(Object o : jsonArray){
					JSONObject jsonObject = JSONObject.parseObject(String.valueOf(o));
					int status = jsonObject.getInteger("chapterState");//是否可读    0.可读    1.不可读
					int chapterId = jsonObject.getInteger("chapterId"); //章节的编号
					if(status==1){
						if(strSet.contains(chapterId)){//0 购买过 1没购买过
							//表明已购买
							jsonObject.put("isBuy",0);
						}else{
							jsonObject.put("isBuy",1);
						}
					}
					bookChapterList.add(jsonObject);
					}
			}
		}
		return bookChapterList;
	}


	/**
	 * 根据图书id和章节id获取图书的内容
	 * @param bookId
	 * @param charpterId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getContentByCharpterId(String bookId,String charpterId) throws Exception {
		List<Map<String,Object>> contentList = new ArrayList<Map<String, Object>>();
		//先获取图书的所有章节
		List<Map<String,Object>>  chapterList = bookDao.getContentByCharpterId(bookId,charpterId);

		if(chapterList.size()>0&&!chapterList.isEmpty()){
			for(Map<String,Object> map:chapterList){
				String chapterContent =  String.valueOf(map.get("chapterContent"));
				byte[] buff = new byte[1024];
				//从字符串获取字节写入流
				InputStream is = new ByteArrayInputStream(chapterContent.getBytes("utf-8"));
				int len = -1;
				while(-1 != (len = is.read(buff))) {
					//将字节数组转换为字符串
					String res = new String(buff, 0, len);
					map.put("chapterContent",res);
				}
			}
		}

		return chapterList;
	}



}
