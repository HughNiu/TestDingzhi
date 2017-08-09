package com.dao;

import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowDao {
	private final IMongoDao mongoDao;
	private   int pageSize=10;

	public FollowDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb","follow");
	}
	/**
	 * 添加关注
	 */
	public void addFollow(long uid,long anchorId) throws Exception {
		Map<String,Object> record=new HashMap<String,Object>();
		record.put("unitId", mongoDao.getAutoId("follow_unitId"));
		record.put("uid", uid);
		record.put("anchorId", anchorId);
		mongoDao.insert(record);
//		mongoDao.
	}
	/**
	 * 取消关注
	 */
	public void delFollow(long uid,long anchorId) throws Exception {
		Map<String,Object> cond=new HashMap<String,Object>();
		cond.put("uid", uid);
		cond.put("anchorId", anchorId);
		mongoDao.delete(cond);
	}
	/**
	 * 修改开播时间
	 */
	public void updateAnchrOpenenLiveTime(long anchorId,int status) throws Exception {
		Map<String,Object> cond=new HashMap<String,Object>();
		cond.put("anchorId", anchorId);

		Map<String,Object> record=new HashMap<String,Object>();
		record.put("openLiveTime", System.currentTimeMillis());
		record.put("status", status);
		mongoDao.update(cond, record, false, true);;
	}
	/**
	 * 获取关注
	 */
	public Map<String,Object>  getFollow(long uid,long anchorId) throws Exception {
		Map<String,Object> cond=new HashMap<String,Object>();
		cond.put("uid", uid);
		cond.put("anchorId", anchorId);
		return mongoDao.findOne(cond);
	}
	/**
	 * 我关注的用户列表
	 */
	public List<Map<String, Object>> queryFollow(long uid,int page,long timestamp) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("uid", uid);
//		Map<String, Object> gidSet = new HashMap<String, Object>();
//		gidSet.put("$lt", timestamp == 0 ? System.currentTimeMillis() : timestamp);
//		cond.put("timestamp", gidSet);
		Map<String, Object> order = new HashMap<String, Object>();
		order.put("openLiveTime", -1);// 最大的时间在最前面
//		page=page<=1?(0):page-1;
		int start=page<=1?0:(page-1)*pageSize;
		return mongoDao.findList(cond, order, start, pageSize);
	}
	/**
	 * 我的粉丝列表
	 */
	public List<Map<String, Object>> queryFans(long anchorId,int page,long timestamp) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("anchorId", anchorId);
		Map<String, Object> gidSet = new HashMap<String, Object>();
		gidSet.put("$lt", timestamp == 0 ? System.currentTimeMillis() : timestamp);
		cond.put("timestamp", gidSet);
		Map<String, Object> order = new HashMap<String, Object>();
		order.put("timestamp", -1);// 最大的时间在最前面
		page=page<=1?(0):page-1;
		return mongoDao.findList(cond, order, 0, pageSize);
	}
	/**
	 * 主播的粉丝数量
	 */
	public int  getAnchorFansNumber(long anchorId) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("anchorId", anchorId);
		return mongoDao.getCount(cond);
	}
	/**
	 * 我关注的主播数量
	 */
	public int  getFollowNumber(long uid) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("uid", uid);
		return mongoDao.getCount(cond);
	}
	/**
	 * 我的关注数量
	 */
	public int  queryFollow(long uid) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("uid", uid);
		return mongoDao.getCount(cond);
	}
}
