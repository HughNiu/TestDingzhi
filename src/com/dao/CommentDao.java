package com.dao;

import com.mongodb.*;
import com.zw.zcf.dao.mongo.IMongoDao;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 云瑞 on 2017/8/7.
 */
public class CommentDao {
    private final IMongoDao mongoDao;
    private   int pageSize=10;
    String dbname = "dingzhidb";
    String table = "cust_comment";

    public CommentDao() {
        mongoDao = DaoFactory.getMongoDao(dbname,table);
    }

    /**
     * 评论回复信息查询
     * @param appId
     * @param type
     * @param pageNo
     * @param timestamp
     * @return
     */
    public List<Map<String, Object>> findCommentList (String appId,int type,int pageNo,long timestamp) {

        Map<String, Object>  cond = new HashedMap();
        cond.put("appId",appId);
        cond.put("commentType",type);
        cond.put("status",0);
        cond.put("replyList.status",0);

        List<String> fields = new ArrayList<String>();
        fields.add("id");
        fields.add("userId");
        fields.add("userName");
        fields.add("userImg");
        fields.add("content");
        fields.add("accentId");
        fields.add("replyList");

        Map<String, Object>  sort = new HashedMap();
        sort.put("timestamp",-1);

        pageNo=pageNo<=1?(0):pageNo-1;
        return mongoDao.findList(cond,fields,sort,pageNo*pageSize,pageSize);
    }

    /**
     * 评论添加
     * @param record
     * @throws Exception
     */
    public void addComment(Map<String,Object> record) throws Exception {
        record.put("id",mongoDao.getAutoId("id"));
        mongoDao.insert(record);
    }

    /**
     * 回复添加
     * @param accentId
     * @param record
     * @throws Exception
     */
    public void addCommentReply(Long accentId,Map<String,Object> record) throws Exception {
        record.put("id",mongoDao.getAutoId("id"));
//        Map<String, Object>  cond = new HashedMap();
//        cond.put("id",accentId);

//        MongodbUtils mdb = new MongodbUtils(dbname,table,accentId,record);
//
//        mdb.insertDocumentAddList(accentId, record);
//        mongoDao.update(cond,record,false,true);
    }

    /**
     * 删除评论
     */
    public void deleteComment (String appId, int id) {
        Map<String, Object>  cond = new HashedMap();
        cond.put("id",id);
        cond.put("appId",appId);

        Map<String, Object>  obj = new HashedMap();
        obj.put("status",1);
        obj.put("updateTtime",new Date().getTime());
        obj.put("timestamp",new Date().getTime());

        mongoDao.update(cond,obj);
    }

    /**
     * 删除回复
     */
    public void deleteReply (String appId, int id,int commentId) {
        Map<String, Object>  cond = new HashedMap();
        cond.put("id",commentId);
        cond.put("replyList.id",id);
        cond.put("appId",appId);

        Map<String, Object>  obj = new HashedMap();
        obj.put("replyList.$.status",1);
        obj.put("replyList.$.updateTtime",new Date().getTime());

        mongoDao.update(cond,obj);
    }
}
