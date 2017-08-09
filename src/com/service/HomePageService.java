package com.service;

import java.util.List;
import java.util.Map;

public interface HomePageService {
	/**
	 * 获取首页书数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,List> getHomePageData(String uid, String appid, int pageNo, long timestamp) throws Exception;

}