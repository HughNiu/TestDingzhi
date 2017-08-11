package com.service.impl;

import com.common.Constants;
import com.dao.DaoFactory;
import com.dao.NewsReadDao;
import com.service.NewsReadService;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsReadServiceImpl implements NewsReadService {
	private static NewsReadDao newsReadDao = new NewsReadDao();
	private static Logger logger = Logger.getLogger(NewsReadServiceImpl.class);
	private IRedisDao redisDao = DaoFactory.getRedisDao();

	/**
	 * 获取首页数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getNewsRead(String uid,int pageNo) throws Exception {
		List<Map<String,Object>> newsReadList = newsReadDao.getNewsRead(uid,pageNo);
		Map<String,Object> newsReadMap = new HashMap<String, Object>();
		newsReadMap.put("pageNo",pageNo);
		newsReadMap.put("pageSize",Constants.PAGE_SIZE);
		newsReadMap.put("newsReadList",newsReadList);
		return newsReadMap;
	}

}
