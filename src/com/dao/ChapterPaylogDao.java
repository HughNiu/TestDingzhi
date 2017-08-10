package com.dao;

import com.common.Constants;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterPaylogDao {
	private final IMongoDao mongoDao;

	public ChapterPaylogDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb", "cust_chapter_paylog");
	}

	/**
	 * 获取用户章节购买记录
	 * @param bookId
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getBookData(String bookId, String uid) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("bookId", Long.parseLong(bookId));
		cond.put("userId",Long.parseLong(uid));
//		cond.put("createTime",-1);//获取最大的时间
		return mongoDao.findOne(cond);
	}
}