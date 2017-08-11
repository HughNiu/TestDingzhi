package com.service.impl;

import com.dao.ChapterPaylogDao;
import com.dao.DaoFactory;
import com.service.ChapterPaylogService;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChapterPaylogServiceImpl implements ChapterPaylogService {
	private static ChapterPaylogDao chapterPaylogDao = new ChapterPaylogDao();
	private static Logger logger = Logger.getLogger(ChapterPaylogServiceImpl.class);
	private IRedisDao redisDao = DaoFactory.getRedisDao();

	/**
	 * 获取用户是否购买过此章节
	 * @param userId
	 * @param bookId
	 * @param charpterId
	 * @return
	 * @throws Exception
	 */
	public Boolean ChapterPaylogBuy(String userId,String bookId, String charpterId) throws Exception {
		Set<Integer> strSet = new HashSet<Integer>();

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

		if(strSet.contains(Integer.parseInt(charpterId))){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Set<Integer> strSet = new HashSet<Integer>();
		strSet.add(1);
		strSet.add(1);
		strSet.add(2);
		strSet.add(3);

		System.out.println(strSet.contains("1"));

	}



}
