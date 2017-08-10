package com.dao;

import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageDao {
	private final IMongoDao mongoDao;

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
