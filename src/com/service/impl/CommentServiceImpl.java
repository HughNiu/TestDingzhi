package com.service.impl;

import com.dao.CommentDao;
import com.dao.PostBarDao;
import com.dao.UserInfoDao;
import com.service.CommentService;
import com.util.DateStampConversion;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import java.util.*;

import static com.common.Constants.PAGE_SIZE;

/**
 * 评论回复
 * Created by 云瑞 on 2017/8/7.
 */
public class CommentServiceImpl implements CommentService {

    private static CommentDao commentDao = new CommentDao();
    private static PostBarDao postBarDao = new PostBarDao();
    private static UserInfoDao userInfoDao = new UserInfoDao();
    private static Logger logger = Logger.getLogger(FollowAnchorServiceImpl.class);

    /**
     * 评论回复查询
     * @param appId
     * @param type
     * @param pageNo
     * @param timestamp
     * @return
     * @throws Exception
     */
    public Map<String, Object> findCommentList (String appId,int type,int pageNo,long timestamp) throws Exception {
        Map<String,Object> result = new HashedMap();

        List<Map<String, Object>> commentList = commentDao.findCommentList(appId, type, pageNo, timestamp);

        for (Map<String, Object> map : commentList) {
            Long userId = (Long) map.get("userId");
            Map<String, Object> user = userInfoDao.getAllInfoById(userId);

            String time = DateStampConversion.getTime(map.get("createTime").toString());
            map.put("creataTime",time);

            Map<String, Object> userInfo = new HashMap<String, Object>();
            userInfo.put("userId",userId);
            userInfo.put("nickname",(String) user.get("nickname"));
            userInfo.put("avatar",(String) user.get("avatar"));
            map.remove("userId");
            List<Map<String, Object>> replyList = (List<Map<String, Object>>) map.get("replyList");

            List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();

            if(replyList != null && replyList.size() > 0) {
                for (Map<String, Object> reply : replyList) {
                    String replyTime = DateStampConversion.getTime(reply.get("createTime").toString());
                    reply.put("creataTime",replyTime);

                    Integer statue = (Integer)reply.get("status");
                    if(statue == 0) {
                        Long userId1 = (Long) reply.get("userId");
                        Map<String, Object> user1 = userInfoDao.getAllInfoById(userId1);

                        Map<String, Object> replyUserInfo = new HashMap<String, Object>();
                        replyUserInfo.put("userId",(Long) user1.get("userId"));
                        replyUserInfo.put("nickname",(String) user1.get("nickname"));
                        reply.remove("userId");
                        reply.put("userInfo",replyUserInfo);

                        re.add(reply);
                    }
                }
                map.put("replyList",re);
            }
            map.put("userInfo",userInfo);
        }

        result.put("commentList",commentList);
        result.put("pageNo",pageNo);
        result.put("pageSize", PAGE_SIZE);
        return result;
    }

    /**
     * 添加评论回复
     * @param appId
     * @param type
     * @param userId
     * @param accentId
     * @param comtent
     * @throws Exception
     */
    public void addComment(String appId,int type,long userId,long accentId,String comtent) throws Exception {
        Map<String, Object> user = userInfoDao.getAllInfoById(userId);

        Map<String,Object> record=new HashMap<String,Object>();
        record.put("content",comtent);
        record.put("userId",userId);
        record.put("status",0);
        record.put("createTime",new Date().getTime());
        record.put("accentId",accentId);
        if(type == 3) {
            commentDao.addCommentReply(accentId,record);
        }else {
            record.put("commentType",type);
            record.put("updateTime",new Date().getTime());
            record.put("timestamp",new Date().getTime());
            record.put("appId",appId);
            commentDao.addComment(record);
            postBarDao.commentCountAddOne(accentId);
        }
    }

    /**
     * 删除评论
     */
    public void deleteComment (String appId, long id) {
        commentDao.deleteComment(appId,id);
    }


    /**
     * 删除回复
     */
    public void deleteReply (String appId, long id,int commentId) {
        commentDao.deleteReply(appId,id,commentId);
    }

    public void getMyReply() {
        List<Map<String, Object>> commentList = commentDao.getMyReply("info", 1L);
    }
}
