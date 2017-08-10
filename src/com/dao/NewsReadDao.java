package com.dao;

import com.common.Constants;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最近阅读
 */
public class NewsReadDao {
	private final IMongoDao mongoDao;

	public NewsReadDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb","cust_news_read");
	}

	/**
	 * 根据用户id获取最近阅读
	 * @param uid
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getNewsRead(String uid,int pageNo) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("userId", Long.parseLong(uid));

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("readTime", -1);// 最大的时间在最前面
		pageNo=pageNo<=1?(0):pageNo-1;
		return  mongoDao.findList(cond,order, Constants.PAGE_SIZE *pageNo,Constants.PAGE_SIZE);
	}

	/**
	 * 根据bookid和用户uid获取阅读的章节
	 * @param bookId
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getNewsReadByBidAndUid(String bookId,String uid) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("userId", Long.parseLong(uid));
		cond.put("bookId", bookId);

		List<String> fileList = new ArrayList<String>();
		fileList.add("chapterId");

		return  mongoDao.findOne(cond,fileList);
	}

}
