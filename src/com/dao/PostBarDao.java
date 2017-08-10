package com.dao;

import com.common.Constants;
import com.mongodb.BasicDBObject;
import com.zw.zcf.dao.mongo.IMongoDao;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

/**
 * 帖子动态文章公告表
 */
public class PostBarDao {
	private final IMongoDao mongoDao;

	public PostBarDao() {
		mongoDao = DaoFactory.getMongoDao("dingzhidb","cust_post_bar");
	}

	/**
	 * 帖子动态文章公告表
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPostBarByType(int type,String appid,int pageNo,long timestamp) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("type", type);
		cond.put("appId",appid);
		cond.put("status", 0);//正常

		if(type==4){//区别帖子
			cond.put("startTime", new BasicDBObject("$lte", System.currentTimeMillis()));// <=
			cond.put("endTime", new BasicDBObject("$gte", System.currentTimeMillis()));// >=
		}

		List<String> fileList = new ArrayList<String>();
		fileList.add("id");
		fileList.add("userId");
		fileList.add("title");
		fileList.add("coverImg");
		fileList.add("content");
		fileList.add("commentImg");
		fileList.add("readCount");
		fileList.add("commentCount");
		fileList.add("jumpUrl");
		fileList.add("type");
		fileList.add("startTime");
		fileList.add("timestamp");

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("timestamp", -1);// 最大的时间在最前面
		pageNo=pageNo<=1?(0):pageNo-1;
		return  mongoDao.findList(cond,fileList,order, Constants.PAGE_SIZE *pageNo,Constants.PAGE_SIZE);
	}


	/**
	 *获取所有的热帖
	 * @param appid
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPostBar(String appid,int pageNo) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("appId",appid);
		cond.put("status", 0);//正常
		cond.put("isHot",0);//0是  1否   当type为1时，isHot默认为1， type不为1时，isHot默认为0

		List<String> fileList = new ArrayList<String>();
		fileList.add("id");
		fileList.add("userId");
		fileList.add("title");
		fileList.add("coverImg");
		fileList.add("content");
		fileList.add("commentImg");
		fileList.add("readCount");
		fileList.add("commentCount");
		fileList.add("jumpUrl");
		fileList.add("type");
		fileList.add("createTime");
		fileList.add("updateTime");
		fileList.add("timestamp");


		Map<String, Object> order = new HashMap<String, Object>();
		order.put("updateTime", -1);// 最大的时间在最前面
		pageNo=pageNo<=1?(0):pageNo-1;
		List<Map<String,Object>> postBarMap =  mongoDao.findList(cond,fileList,order,Constants.PAGE_SIZE *pageNo,Constants.PAGE_SIZE);
		return postBarMap;
	}

    /**
     * 评论数修改
     * @param accentId
     */
    public void commentCountAddOne(long accentId) {
        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("id",accentId);

        List<String> fields = new ArrayList<String>();
        fields.add("commentCount");
        Map<String, Object> one = mongoDao.findOne(cond, fields);
        Integer commentCount =  (Integer) one.get("commentCount");

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("commentCount",(commentCount+1));
        obj.put("updateTtime",new Date().getTime());
        obj.put("timestamp",new Date().getTime());

        mongoDao.update(cond,obj);
    }

    /**
     * 动态/文章/帖子/公告 添加
     */
    public void addPostBar(Map<String,Object> map) throws Exception {
        map.put("id",mongoDao.getAutoId("id"));
        map.put("status",0);
        map.put("readCount",0);
        map.put("commentCount",0);
        map.put("createTime",new Date().getTime());
        map.put("updateTime",new Date().getTime());
        map.put("timestamp",new Date().getTime());
        mongoDao.insert(map);
    }

	/**
	 * 动态/文章/帖子 删除
	 */
	public void deletePostBar (String appId,int type,long id) {
		Map<String, Object> cond = new HashedMap();
		cond.put("appId",appId);
		cond.put("type",type);
		cond.put("id",id);

		Map<String, Object> obj = new HashedMap();
		obj.put("updateTime",new Date().getTime());
		obj.put("timestamp",new Date().getTime());
		obj.put("status",1);

		mongoDao.update(cond,obj);
	}

    /**
     * 根据userId 查询 动态/文章/帖子
     */
    public List<Map<String, Object>> findPostBarByUserId (String appId, int type, long userId, int pageNo) {
        Map<String, Object> cond = new HashedMap();
        cond.put("appId",appId);
        cond.put("type",type);
        cond.put("userId",userId);

        List<String> fields = new ArrayList<String>();
        fields.add("id");
        fields.add("userId");
        fields.add("title");
        fields.add("coverImg");
        fields.add("content");
        fields.add("commentImg");
        fields.add("readCount");
        fields.add("commentCount");
        fields.add("jumpUrl");
        fields.add("type");
        fields.add("createTime");
        fields.add("updateTime");
        fields.add("timestamp");

        Map<String, Object> obj = new HashedMap();
        obj.put("timestamp",-1);
        pageNo = pageNo == 1 ? 0 : pageNo;
        return mongoDao.findList(cond,fields,obj,pageNo*Constants.PAGE_SIZE,Constants.PAGE_SIZE);
    }

    /**
     * 根据ID 获取详细信息
     */
    public Map<String, Object> findPostBarById (long id) {
        Map<String,Object> cone = new HashedMap();
        cone.put("id",id);

        Map<String,Object> obj = new HashedMap();
        obj.put("timestamp",-1);

        return mongoDao.findOne(cone,obj);
    }
}
