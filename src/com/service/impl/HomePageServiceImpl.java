package com.service.impl;

import com.dao.*;
import com.service.HomePageService;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageServiceImpl implements HomePageService {
	private static HomePageDao homePageDao = new HomePageDao();
	private static PostBarDao postBarDao = new PostBarDao();
	private static NewsReadDao newsReadDao = new NewsReadDao();
	private static RecentUpdatesDao recentUpdatesDao = new RecentUpdatesDao();
	private static UserInfoDao userInfoDao = new UserInfoDao();
	private static Logger logger = Logger.getLogger(HomePageServiceImpl.class);
	private IRedisDao redisDao = DaoFactory.getRedisDao();

	/**
	 * 获取首页数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,List> getHomePageData(String uid,String appid,int pageNo,long timestamp) throws Exception {
		Map homePageMap = new HashMap();
		//根据类型获取公告(type=4 表示公告)
		List<Map<String,Object>> postBarList = postBarDao.getPostBarByType(4,appid,1,timestamp);
//		homePageMap.put("noticeCustPostBar",postBarList);

		//获取最近阅读(20条记录)
		List<Map<String,Object>> newsReadList = newsReadDao.getNewsRead(uid,1);
//		homePageMap.put("newsRead",newsReadList);

		//获取最近更新记录()
		List<Map<String,Object>> recentUpdateList =  recentUpdatesDao.getRecentUpdates(uid,1,appid);
//		homePageMap.put("recentUpdates",recentUpdateList);

		//获取帖子/动态/文章混排
		List<Map<String,Object>> allPostBarList = postBarDao.getPostBar(appid,pageNo);

		//处理数据
		//1.用户/作者信息
		if(allPostBarList.size()>0&&!allPostBarList.isEmpty()){
			for(Map<String,Object> map:allPostBarList){
				long userId = Long.parseLong(map.get("userId").toString());
				map.put("userInfo",userInfoDao.getUserInfoById(userId));
			}
		}

		homePageMap.put("allCustPostBar",allPostBarList);


		return homePageMap;
	}

}
