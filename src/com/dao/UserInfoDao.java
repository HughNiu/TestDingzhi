package com.dao;

import com.vo.Product;
import com.vo.UserInfo;
import com.zw.zcf.dao.mongo.IMongoDao;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luozi on 2017/7/20.
 */
public class UserInfoDao {
    private final IMongoDao mongoDao;

    public UserInfoDao() {
        mongoDao = DaoFactory.getFollowMongoDao("dingzhidb", "cust_user");
    }

    /**
     * 根据ID查找用户
     * @param userId 用户id
     * @return 返回用户所有信息
     */
    public Map<String,Object> getAllInfoById(long userId) throws Exception{
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("userId", userId);
        Map<String, Object> userInfo = mongoDao.findOne(query);
        return userInfo;
    }

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    public Map<String,Object> getUserInfoByUserId(long userId) throws Exception{
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("userId", userId);
        List<String> filedList = new ArrayList<String>();
        filedList.add("userId");
        filedList.add("nickname");
        filedList.add("avatar");
        filedList.add("sex");
        filedList.add("profile");
        filedList.add("zodiac");
        Map<String, Object> userInfo = mongoDao.findOne(query,filedList);
        return userInfo;
    }

    /**
     * 根据id修改用户信息
     * @param userId 用户id
     * @param newUserInfo 新用户信息
     */
    public void updateUserById(Long userId, Map<String, Object> newUserInfo) {
        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("userId", userId);
        mongoDao.update(cond, newUserInfo);
    }

    /**
     * 根据用户id获取用户类型
     * @param userId
     * @return
     * @throws Exception
     */
    public int getTypeById(long userId) throws Exception{
        Map<String, Object> userInfo = getAllInfoById(userId);
        return MapUtils.getIntValue(userInfo, "type", 0);
    }
}
