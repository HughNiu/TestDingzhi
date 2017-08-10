package com.dao;

import com.common.Constants;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDao {
	private final IMongoDao mongoDao;

	public BookDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb", "cust_book");
	}

	/**
	 * 获取所有图书
	 * @param appId
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getBookData(String appId, int pageNo) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("appId", appId);
		cond.put("bookState", 0);//上架.

		List<String> filedList = new ArrayList<String>();
		filedList.add("id");
		filedList.add("bookName");
		filedList.add("authorId");
		filedList.add("bookImg");
		filedList.add("introduction");
		filedList.add("wordCount");
		filedList.add("bookNewsId");
		filedList.add("bookNewsName");
		filedList.add("bookNewsTime");
		filedList.add("bookType");

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("bookNewsTime", -1);// 最大的时间在最前面
		pageNo = pageNo <= 1 ? (0) : pageNo - 1;
		return mongoDao.findList(cond,filedList, order, Constants.PAGE_SIZE * pageNo, Constants.PAGE_SIZE);
	}

	/**
	 * 获取图书所有目录
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getBookChapter(String bookId) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("id", Long.parseLong(bookId));

		List<String> filedList = new ArrayList<String>();
		filedList.add("bookChapterList");

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("bookNewsTime", -1);// 最大的时间在最前面
		return mongoDao.findList(cond,filedList, order, 0, -1);
	}
}