package com.dao;

import com.vo.UserInfo;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luozi on 2017/7/20.
 */
public class UserInfoDao {
    private final IMongoDao mongoDao;
    private int pageSize = 20;

    public UserInfoDao() {
        mongoDao = DaoFactory.getFollowMongoDao("dingzhidb", "cust_user");
    }



    public void addUserInfo(UserInfo userInfo) throws Exception {
        Map<String,Object> record=new HashMap<String,Object>();
        record.put("id", mongoDao.getAutoId("id"));
        record.put("nickName",userInfo.getNickName());
        record.put("type",userInfo.getType());
        record.put("regType",userInfo.getRegType());
        record.put("pwd",userInfo.getPwd());
        record.put("sex",userInfo.getSex());
        record.put("birthday",userInfo.getBirthday());
        record.put("headImg",userInfo.getBirthday());
        record.put("province",userInfo.getProvince());
        record.put("city",userInfo.getCity());
        record.put("region",userInfo.getRegion());
        record.put("star",userInfo.getStar());
        record.put("authorId",userInfo.getAuthorId());
        record.put("weiXin",userInfo.getWeiXin());
        record.put("qq",userInfo.getQq());
        record.put("enabled",userInfo.getEnabled());
        record.put("appid",userInfo.getAppid());
        record.put("createDate",userInfo.getCreateDate());
        record.put("createUserID",userInfo.getCreateUserID());
        record.put("latestDate",userInfo.getLatestDate());
        mongoDao.insert(record);
    }

    /**
     * 根据ID查找用户, 返回用户所有字段
     * @param userId 用户id
     * @return
     */
    public Map<String,Object> getUserInfoById(long userId) throws Exception{
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("userId", userId);
        List<String> filedList = null;
        Map<String, Object> userInfo = mongoDao.findOne(query, filedList);
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
}
