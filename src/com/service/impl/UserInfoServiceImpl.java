package com.service.impl;

import com.dao.DaoFactory;
import com.dao.UserInfoDao;
import com.service.UserInfoService;
import com.vo.UserInfo;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by luozi on 2017/7/20.
 */
public class UserInfoServiceImpl implements UserInfoService {
    private static UserInfoDao userInfoDao = new UserInfoDao();
    private static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
    private IRedisDao redisDao = DaoFactory.getRedisDao();
    /**
     * 添加用户
     * @param userInfo 用户
     */
    public void addUserInfo(UserInfo userInfo) throws Exception{
//        userInfoDao.addUserInfo(userInfo);
        logger.info("-----addUserInfo----" + userInfo.toString());
    }

    /**
     * 根据ID查找用户
     * @param id ID
     * @return
     */
    public Map<String,Object> getUserInfoById(long id) throws Exception{
        Map<String,Object> userInfo=userInfoDao.getUserInfoById(id);
        logger.info("-----addUserInfo---userInfo=" + userInfo);
        return userInfo;
    }
}
