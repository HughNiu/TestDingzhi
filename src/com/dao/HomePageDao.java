package com.dao;

import com.zw.zcf.dao.mongo.IMongoDao;

public class HomePageDao {
	private final IMongoDao mongoDao;
	private   int pageSize=10;

	public HomePageDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb","");
	}

	/**
	 * 获取首页数据
	 * @throws Exception
	 */
	public void getHomePageData() throws Exception {

	}
}
