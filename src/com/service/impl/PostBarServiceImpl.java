package com.service.impl;

import com.dao.PostBarDao;
import com.service.PostBarService;
import com.util.DateStampConversion;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.common.Constants.PAGE_SIZE;

/**
 * Created by 云瑞 on 2017/8/9.
 */
public class PostBarServiceImpl implements PostBarService {

    private static PostBarDao postBarDao = new PostBarDao();
    private static Logger logger = Logger.getLogger(PostBarServiceImpl.class);

    /**
     * 动态/文章/帖子/公告 查询
     * @param type
     * @param appid
     * @param pageNo
     * @param timestamp
     * @return
     * @throws Exception
     */
    public Map<String,Object> getPostBarByType(int type, String appid, int pageNo, long timestamp) throws Exception {
        Map<String,Object> result = new HashedMap();
        List<Map<String, Object>> postBar = postBarDao.getPostBarByType(type, appid, pageNo, timestamp);
        result.put("postBarList",postBar);
        result.put("pageNo",pageNo);
        result.put("pageSize", PAGE_SIZE);
        return result;
    }

    /**
     * 动态/文章/帖子/公告 添加
     */
    public void addPostBar(Map<String,Object> map) throws Exception {
        postBarDao.addPostBar(map);
    }

    /**
     * 动态/文章/帖子/公告 删除
     */
    public void deletePostBar (String appId,int type,long id) {
        postBarDao.deletePostBar(appId,type,id);
    }

    public Map<String, Object> findPostBarByUserId (String appId, int type, long userId, int pageNo) throws ParseException {
        Map<String,Object> result = new HashedMap();
        List<Map<String, Object>> postBar = postBarDao.findPostBarByUserId(appId, type, userId, pageNo);
        if(postBar != null && postBar.size() > 0) {
            for(Map<String,Object> map : postBar) {
                map.put("createTime", DateStampConversion.getTime(map.get("createTime").toString()));
            }
        }
        result.put("postBarList",postBar);
        result.put("pageNo",pageNo);
        result.put("pageSize",PAGE_SIZE);
        return result;
    }

    /**
     * 我发布的文章动态
     */
    public Map<String, Object> getArticleDynamic(String appId, long userId, int pageNo) throws ParseException {
        Map<String,Object> result = new HashedMap();
        List<Map<String, Object>> postBar = postBarDao.getArticleDynamic(appId, userId, pageNo);
        if(postBar != null && postBar.size() > 0) {
            for (Map<String, Object> map : postBar) {
                map.put("createTime", DateStampConversion.getTime(map.get("createTime").toString()));
            }
        }
        result.put("postBarList",postBar);
        result.put("pageNo",pageNo);
        result.put("pageSize",PAGE_SIZE);
        return result;
    }
}
