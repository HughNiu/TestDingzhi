package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.Constants;
import com.dao.*;
import com.service.BookService;
import com.service.HomePageService;
import com.util.DateStampConversion;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookeServiceImpl implements BookService {
	private static BookDao bookDao = new BookDao();
	private static UserInfoDao userInfoDao = new UserInfoDao();
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
		JSONArray jsonArray = new JSONArray();

		List<Map<String,Object>>  chapterList = bookDao.getBookChapter(bookId);

		if(chapterList.size()>0&&!chapterList.isEmpty()){
			for(Map<String,Object> map :chapterList){
				jsonArray = JSONArray.parseArray(String.valueOf(map.get("bookChapterList")));
				for(Object o : jsonArray){
					JSONObject jsonObject = JSONObject.parseObject(String.valueOf(o));
					int status = jsonObject.getInteger("chapterState");//是否可读    0.可读    1.不可读
					int chapterId = jsonObject.getInteger("chapterId"); //章节的编号
					if(status==1){
						//查询用户是否购买过此章节
						Map<String,Object> chapterPaylogMap =  chapterPaylogDao.getBookData(bookId,userId);
						if(chapterPaylogMap!=null){
							int endChapterId =  Integer.parseInt(String.valueOf(chapterPaylogMap.get("endChapterId")));
							if(chapterId<endChapterId){//0 购买过 1没购买过
								//表明已购买
								jsonObject.put("isBuy",0);
							}else{
								jsonObject.put("isBuy",1);
							}
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


}
