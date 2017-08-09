package com.service;

import com.vo.UserInfo;

import java.util.Map;

/**
 * Created by luozi on 2017/7/20.
 */
public interface UserInfoService {
    /**
     * 添加用户
     * @param userInfo 用户
     */
    public void addUserInfo(UserInfo userInfo) throws Exception;

    /**
     * 根据ID查找用户
     * @param id ID
     * @return
     */
    public Map<String,Object> getUserInfoById(long id) throws Exception;
}
