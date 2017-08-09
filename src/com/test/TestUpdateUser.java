package com.test;

import com.dao.UserInfoDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-08 0008.
 */
public class TestUpdateUser {
    private static UserInfoDao userDao = new UserInfoDao();
    public static void main(String[] args) {
        Map<String, Object> newUserInfo = new HashMap<String, Object>();
        newUserInfo.put("nickname", "胡图图");
        userDao.updateUserById(100L, newUserInfo);
    }
}
