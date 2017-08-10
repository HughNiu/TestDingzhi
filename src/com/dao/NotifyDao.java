package com.dao;

import com.common.Constants;
import com.zw.zcf.dao.mongo.IMongoDao;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-08 0008.
 */
public class NotifyDao {

    private final IMongoDao mongoDao;

    private UserInfoDao userDao = new UserInfoDao();

    private NotifyDao() {
        mongoDao = DaoFactory.getMongoDao("dingzhidb", "cust_notify");
    }

    public static NotifyDao notifyDao = new NotifyDao();

    /**
     * 新增通知记录
     * @param toUserId 通知接收的用户id
     * @param content 通知内容(回复时为回复内容, 评论时为评论内容, 打赏时为打赏金额)
     * @param type 通知类型
     * @param fromUserId 发出通知者id
     * @param source 通知来源 key必须包含title, content, createTime, 字段值可为空
     * @param appId
     */
    public void insert(long toUserId, String content, int type, long fromUserId,
                       Map<String, Object> source, String appId) throws Exception {
        Map<String, Object> notification = new HashMap<String, Object>();
        notification.put("notifyId", mongoDao.getAutoId("id"));
        notification.put("toUserId", toUserId);
        notification.put("content", content);
        notification.put("type", type);
        notification.put("fromUserId", fromUserId);
        notification.put("source", source);
        notification.put("appId", appId);
        notification.put("status", Constants.NOTIFY_UNREAD);
        long now = System.currentTimeMillis();
        notification.put("createTime", now);
        notification.put("timestamp", now);
        mongoDao.insert(notification);
    }

    /**
     * 获取作者用户未读消息数
     * @param userId
     * @return
     */
    public Map<String, Object> getAuthorUnreadNotify(long userId) {
        Map<String, Object> notifications = new HashMap<String, Object>();
        int sysCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_SYS, Constants.NOTIFY_UNREAD);
        notifications.put("system", sysCnt);
        int postReplyCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_POST_REPLY, Constants.NOTIFY_UNREAD);
        int cmtReplyCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_CMT_REPLY, Constants.NOTIFY_UNREAD);
        notifications.put("reply", postReplyCnt + cmtReplyCnt);
        int bookCmtCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_BOOK_CMT, Constants.NOTIFY_UNREAD);
        notifications.put("comment", bookCmtCnt);
        int rewardArtCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_REWARD_ARTICLE, Constants.NOTIFY_UNREAD);
        int rewardBookCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_REWARD_BOOK, Constants.NOTIFY_UNREAD);
        notifications.put("reward", rewardArtCnt + rewardBookCnt);
        return notifications;
    }

    /**
     * 获取普通用户未读消息数
     * @param userId
     * @return
     */
    public Map<String, Object> getUserUnreadNotify(long userId) {
        Map<String, Object> notifications = new HashMap<String, Object>();
        int sysCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_SYS, Constants.NOTIFY_UNREAD);
        notifications.put("system", sysCnt);
        int postReplyCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_POST_REPLY, Constants.NOTIFY_UNREAD);
        int cmtReplyCnt = getCntByTypeAndStatus(userId, Constants.NOTIFY_CMT_REPLY, Constants.NOTIFY_UNREAD);
        notifications.put("reply", postReplyCnt + cmtReplyCnt);
        return notifications;
    }

    /**
     * 根据类型和状态获取通知数量
     * @param type   通知类型
     * @param status 0-未读, 1-已读, -1=全部
     * @return
     */
    private int getCntByTypeAndStatus(long userId, int type, int status) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("type", 1);

        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("userId", userId);
        cond.put("type", type);
        if (status != -1) {
            cond.put("status", status);
        }

        Map<String, Object> initial = new HashMap<String, Object>();
        initial.put("count", 0);

        String reduce = "function(doc, out) {out.count++;}";

        List<Map<String, Object>> byGroup = mongoDao.findByGroup(key, cond, initial, reduce, null);
        if (byGroup == null || byGroup.size() < 1) {
            byGroup = new ArrayList<Map<String, Object>>();
            Map<String, Object> cntMap = new HashMap<String, Object>();
            cntMap.put(type + "", 0);
            byGroup.add(cntMap);
        }
        return MapUtils.getIntValue(byGroup.get(0), "count", 0);
    }

    /**
     * 分页获取通知列表
     * @param userId 用户id
     * @param pageNo 页号, 从1开始
     * @param pageSize 页大小
     * @param types 通知类型
     * @return
     */
    public List<Map<String, Object>> getByPage(long userId, int pageNo, int pageSize, List<Integer> types) {
        Map<String, Object> cond = new HashMap<String, Object>();
        List<Map<String, Object>> typeList = new ArrayList<Map<String, Object>>();
        for (int type:types) {
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("type", type);
            typeList.add(temp);
        }
        cond.put("$or", typeList);
        cond.put("userId", userId);

        Map<String, Object> order = new HashMap<String, Object>();
        order.put("timestamp", -1); // 时间戳倒序

        pageNo = (pageNo <= 1 ? 0 : (pageNo - 1));
        List<Map<String, Object>> notifications = mongoDao.findList(cond, null, order, pageSize * pageNo, pageSize);
        return notifications;
    }
}
