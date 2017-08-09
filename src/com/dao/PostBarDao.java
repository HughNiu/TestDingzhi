package com.dao;

import com.mongodb.BasicDBObject;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.*;

/**
 * 帖子动态文章公告表
 */
public class PostBarDao {
	private final IMongoDao mongoDao;
	private   int pageSize=10;

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
			cond.put("startTime", new BasicDBObject("$lte", System.currentTimeMillis()/1000));// <=
			cond.put("endTime", new BasicDBObject("$gte", System.currentTimeMillis()/1000));// >=
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
		fileList.add("isHot");
		fileList.add("jumpUrl");
		fileList.add("type");
		fileList.add("startTime");
		fileList.add("timestamp");

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("timestamp", -1);// 最大的时间在最前面
		pageNo=pageNo<=1?(0):pageNo-1;
		return  mongoDao.findList(cond,fileList,order,pageSize*pageNo,pageSize);
	}


    /**
     *
     * @param appid
     * @param pageNo
     * @return
     * @throws Exception
     */
	public List<Map<String,Object>> getPostBar(String appid,int pageNo) throws Exception {
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("appId",appid);
		cond.put("status", 0);//正常

		List<String> fileList = new ArrayList<String>();
		fileList.add("id");
		fileList.add("userId");
		fileList.add("title");
		fileList.add("coverImg");
		fileList.add("content");
		fileList.add("commentImg");
		fileList.add("readCount");
		fileList.add("commentCount");
		fileList.add("isHot");
		fileList.add("jumpUrl");
		fileList.add("type");
		fileList.add("createTime");
        fileList.add("updateTime");
		fileList.add("timestamp");


		Map<String, Object> order = new HashMap<String, Object>();
		order.put("timestamp", -1);// 最大的时间在最前面
		pageNo=pageNo<=1?(0):pageNo-1;
		return  mongoDao.findList(cond,fileList,order,pageSize*pageNo,pageSize);
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
}
