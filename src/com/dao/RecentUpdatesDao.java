package com.dao;

import com.common.Constants;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最新更新
 */
public class RecentUpdatesDao {
	private final IMongoDao mongoDao;

	public RecentUpdatesDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb","cust_book");
	}
	private static NewsReadDao newsReadDao = new NewsReadDao();


	/**
	 * 最新更新
	 * @param uid
	 * @param pageNo
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getRecentUpdates(String uid,int pageNo,String appId) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("appId", appId);

		List<String> fileList = new ArrayList<String>();
		fileList.add("id");
		fileList.add("bookName");
		fileList.add("bookImg");
		fileList.add("introduction");
		fileList.add("bookNewestId");
		fileList.add("bookNewestName");

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("bookNewestTime", -1);// 最大的时间在最前面
		pageNo=pageNo<=1?(0):pageNo-1;
		List<Map<String,Object>> recentUpdatesList = mongoDao.findList(cond,fileList,order, Constants.PAGE_SIZE *pageNo,Constants.PAGE_SIZE);

		//根据图书获取用户最近阅读章节，用来跳转到用户的章节
		if(recentUpdatesList.size()>0&&!recentUpdatesList.isEmpty()){
			for(Map<String,Object> map:recentUpdatesList){
				Object bookId = map.get("bookId");
				//根据id获取最近阅读章节
				Map<String,Object> newsRead = newsReadDao.getNewsReadByBidAndUid(bookId.toString(),uid);
				if(newsRead!=null){
					map.put("chapterId",newsRead.get("chapterId"));
				}else{
					map.put("chapterId",0);
				}
			}
		}
		return recentUpdatesList;
	}
}
