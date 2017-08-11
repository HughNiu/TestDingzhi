package com.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by 云瑞 on 2017/8/9.
 */
public interface PostBarService {

    Map<String,Object> getPostBarByType(int type, String appid, int pageNo, long timestamp) throws Exception;

    void addPostBar(Map<String,Object> map) throws Exception;

    void deletePostBar (String appId,int type,long id);

    Map<String, Object> findPostBarByUserId (String appId, int type, long userId, int pageNo) throws ParseException;

    Map<String, Object> getArticleDynamic(String appId, long userId, int pageNo) throws ParseException;
}
