package com.service.impl;

import com.common.Constants;
import com.dao.*;
import com.service.HomePageService;
import com.util.DateStampConversion;
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
		homePageMap.put("noticeCustPostBar",postBarList);

		//获取最近阅读(20条记录)
		List<Map<String,Object>> newsReadList = newsReadDao.getNewsRead(uid,1);
		homePageMap.put("newsRead",newsReadList);

		//获取最近更新记录()
		List<Map<String,Object>> recentUpdateList =  recentUpdatesDao.getRecentUpdates(uid,1,appid);
		homePageMap.put("recentUpdates",recentUpdateList);

		//获取帖子/动态/文章混排
		List<Map<String,Object>> allPostBarList = postBarDao.getPostBar(appid,pageNo);

		//处理数据

		if(allPostBarList.size()>0&&!allPostBarList.isEmpty()){
			for(Map<String,Object> map:allPostBarList){
				//1.用户/作者信息
				long userId = Long.parseLong(map.get("userId").toString());
				map.put("userInfo",userInfoDao.getUserInfoByUserId(userId));

				//时间信息
				String time = DateStampConversion.stampToDate(DateStampConversion.YYYY_MM_DD_HH_MM_SS,map.get("updateTime").toString());

				//当前时间
				String currentTime = DateStampConversion.getCurrentTime(DateStampConversion.YYYY_MM_DD_HH_MM_SS);

				if(DateStampConversion.getDay(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)==DateStampConversion.getDay(DateStampConversion.YYYY_MM_DD_HH_MM_SS,currentTime)) {//当前天相同
					map.put("date",DateStampConversion.getHour(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+":"+ DateStampConversion.getMinutes(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time));
					continue;
				}
				else if(DateStampConversion.getMonth(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)==DateStampConversion.getMonth(DateStampConversion.YYYY_MM_DD_HH_MM_SS,currentTime)) {//当前月相同
					map.put("date", DateStampConversion.getMonth(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+"-"+DateStampConversion.getDay(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+" "
							+DateStampConversion.getHour(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+":" + DateStampConversion.getMinutes(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time));
					continue;
				}
				else if(DateStampConversion.getYear(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)==DateStampConversion.getYear(DateStampConversion.YYYY_MM_DD_HH_MM_SS,currentTime)) {//当前年相同
					map.put("date", DateStampConversion.getMonth(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+"-"+DateStampConversion.getDay(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+" "
							+DateStampConversion.getHour(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time)+":" + DateStampConversion.getMinutes(DateStampConversion.YYYY_MM_DD_HH_MM_SS,time));
					continue;
				}
				else{
					map.put("date",time);
				}
			}
		}
		Map<String,Object> postBarMap = new HashMap<String, Object>();
		postBarMap.put("pageNo",pageNo);
		postBarMap.put("pageSize", Constants.PAGE_SIZE);
		postBarMap.put("postBar",allPostBarList);
		homePageMap.put("allCustPostBar",postBarMap);

		return homePageMap;
	}

}
