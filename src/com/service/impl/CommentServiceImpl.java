package com.service.impl;

import com.dao.CommentDao;
import com.dao.PostBarDao;
import com.dao.UserInfoDao;
import com.service.CommentService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> findCommentList (String appId,int type,int pageNo,long timestamp) throws Exception {

        List<Map<String, Object>> commentList = commentDao.findCommentList(appId, type, pageNo, timestamp);

        for (Map<String, Object> map : commentList) {
            Long userId = (Long) map.get("userId");
            Map<String, Object> user = userInfoDao.getUserInfoById(userId);

            Map<String, Object> userInfo = new HashMap<String, Object>();
            userInfo.put("userId",userId);
            userInfo.put("userName",(String) user.get("nickname"));
            userInfo.put("userImg",(String) user.get("avatar"));
            map.remove("userId");
            List<Map<String, Object>> replyList = (List<Map<String, Object>>) map.get("replyList");
            int size = 0;
            if(replyList != null && replyList.size() > 0) {
                for (Map<String, Object> reply : replyList) {
                    Long userId1 = (Long) reply.get("userId");
                    Map<String, Object> user1 = userInfoDao.getUserInfoById(userId1);

                    Map<String, Object> replyUserInfo = new HashMap<String, Object>();
                    replyUserInfo.put("userId",(Long) user1.get("userId"));
                    replyUserInfo.put("userName",(String) user1.get("nickname"));
                    reply.remove("userId");
                    reply.put("userInfo",replyUserInfo);
                }
            }
            map.put("userInfo",userInfo);
        }

        return commentList;
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
        Map<String, Object> user = userInfoDao.getUserInfoById(userId);

        Map<String,Object> record=new HashMap<String,Object>();
        record.put("content",comtent);
        record.put("userId",userId);
        record.put("status",0);
        record.put("createTime",new Date().getTime());

        if(type == 3) {
            commentDao.addCommentReply(accentId,record);
        }else {
            record.put("accentId",accentId);
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
    public void deleteComment (String appId, int id) {
        commentDao.deleteComment(appId,id);
    }


    /**
     * 删除回复
     */
    public void deleteReply (String appId, int id,int commentId) {
        commentDao.deleteReply(appId,id,commentId);
    }
}
