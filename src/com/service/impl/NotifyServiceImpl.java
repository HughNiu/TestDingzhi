package com.service.impl;

import com.common.Constants;
import com.dao.NotifyDao;
import com.dao.PostBarDao;
import com.dao.UserInfoDao;
import com.service.NotifyService;
import com.zw.zcf.util.MapUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-11 0011.
 */
public class NotifyServiceImpl implements NotifyService {

    private Logger logger = Logger.getLogger(this.getClass());

    private NotifyDao notifyDao = new NotifyDao();

    private UserInfoDao userInfoDao = new UserInfoDao();

    private PostBarDao postBarDao = new PostBarDao();

    public Map<String, Object> getUnread(long userId) {
        try {
            // 查询用户类型
            int type = userInfoDao.getTypeById(userId);

            if (type == Constants.USER_TYPE_AUTHOR) {
                return notifyDao.getAuthorUnreadNotify(userId);
            } else {
                return notifyDao.getUserUnreadNotify(userId);
            }
        } catch (Exception e) {
            logger.error("NotifyServiceImpl: 获取用户未读通知异常, userId = " + userId, e);
            return null;
        }
    }

    public List<Map<String, Object>> getList(long userId, int type, int pageNo) throws Exception {
        List<Map<String, Object>> notifyList = notifyDao.getByPage(userId, pageNo, type);
        for (Map<String, Object> notify:notifyList) {
            long fromUserId = MapUtils.getLongValue(notify, "fromUserId", 0);
            Map<String, Object> source = MapUtils.getMap(notify, "source");
            Map<String, Object> sourceInfo = getSource(source);
            Map<String, Object> userInfo = userInfoDao.getAllInfoById(userId);
        }
        return null;
    }

    private Map<String,Object> getSource(Map<String, Object> source) {
        Map<String, Object> sourceInfo = new HashMap<String, Object>();
        int type = MapUtils.getIntValue(source, "type");
        long id = MapUtils.getLongValue(source, "id");
        switch (type) {
            case Constants.SOURCE_TYPE_POST:
                break;
            case Constants.SOURCE_TYPE_CMT:
                break;
            case Constants.SOURCE_TYPE_BOOK:
                break;
            case Constants.SOURCE_TYPE_ARTICLE:
                break;
        }
        return null;
    }
}
