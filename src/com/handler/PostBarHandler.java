package com.handler;

import com.service.PostBarService;
import com.service.impl.PostBarServiceImpl;
import com.util.DateStampConversion;
import com.util.check.ResponseResult;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 动态/文章/帖子/公告
 * Created by 云瑞 on 2017/8/9.
 */
public class PostBarHandler extends MultiCommandHandler {

    private static Logger logger = Logger.getLogger(MultiCommandHandler.class);

    static PostBarService postBarService = new PostBarServiceImpl();

    /**
     * 动态/文章/帖子/公告 查询
     */

    public void getPostBarByType(Command cmd) {
        logger.info("-------getPostBarByType-------cmd:" + cmd);
        int type = cmd.getIntParam("type");
        int pageNo = cmd.getIntParam("pageNo");
        long timestamp = cmd.getLongParam("timestamp");
        String appId = cmd.getStringParam("appId");

        try {
            Map<String, Object> postBarList = postBarService.getPostBarByType(type, appId, pageNo, timestamp);
            Response re = new Response();
            re.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            re.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            re.addValue(ResponseResult.DATA,postBarList);
            cmd.setResponse(re);
        } catch (Exception e) {
            logger.error("getPostBarByType----cmd:" +cmd + ",exception:", e);
            Response re = new Response();
            re.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            re.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            re.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(re);
        }
    }

    /**
     * 动态/文章/帖子/公告 添加
     */
    public void addPostBar (Command cmd) throws ParseException {
        int type = cmd.getIntParam("type");
        try {
            Map<String,Object> map = new HashedMap();
            map.put("userId",cmd.getLongParam("userId"));
            map.put("appId",cmd.getStringParam("appId"));
            map.put("type",type);
            if (type == 1) {
                map.put("isHot",1);
                map.put("title",cmd.getStringParam("title"));
                map.put("content",cmd.getStringParam("content"));
                map.put("commentImg",new String[]{"htpp://XXXXX1","htpp://XXXXX2","htpp://XXXXX2"});
            }else if (type == 2) {
                map.put("content",cmd.getStringParam("content"));
                map.put("commentImg",new String[]{"htpp://XXXXX1","htpp://XXXXX2","htpp://XXXXX2"});
                map.put("isHot",0);
            }else if (type == 3) {
                map.put("coverImg",cmd.getStringParam("coverImg"));
                map.put("title",cmd.getStringParam("title"));
                map.put("content",cmd.getStringParam("content"));
                map.put("commentImg",new String[]{"htpp://XXXXX1","htpp://XXXXX2","htpp://XXXXX2"});
                map.put("isHot",0);
            }else if (type == 4) {
                map.put("title",cmd.getStringParam("title"));
                map.put("startTime",Long.parseLong(DateStampConversion.dateToStamp(DateStampConversion.YYYY_MM_DD,"2017-08-20")));
                map.put("endTime",Long.parseLong(DateStampConversion.dateToStamp(DateStampConversion.YYYY_MM_DD,"2017-08-20")));
                map.put("isHot",0);
                map.put("jumpUrl",cmd.getStringParam("jumpUrl"));
            }
            postBarService.addPostBar(map);
            Response re = new Response();
            re.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            re.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            cmd.setResponse(re);
            logger.info("-------addPostBar-------cmd:" + cmd);
        }catch (Exception e) {
            logger.error("addPostBar----cmd:" +cmd + ",exception:", e);
            Response re = new Response();
            re.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            re.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            cmd.setResponse(re);
        }
    }

    /**
     * 动态/文章/帖子/ 删除
     */
    public void deletePostBar (Command cmd) {
        logger.info("-------addPostBar-------cmd:" + cmd);
        String appId = cmd.getStringParam("appId");
        int type = cmd.getIntParam("type");
        long id = cmd.getLongParam("id");
        try {
            postBarService.deletePostBar(appId,type,id);
        }catch (Exception e) {
            logger.error("addPostBar----cmd:" +cmd + ",exception:", e);
        }
    }

    public void findPostBarByUserId (Command cmd) {
        logger.info("-------addPostBar-------cmd:" + cmd);
        String appId = cmd.getStringParam("appId");
        int type = cmd.getIntParam("type");
        long userId = cmd.getLongParam("userId");
        int pageNo = cmd.getIntParam("pageNo");

        Map<String, Object> result = postBarService.findPostBarByUserId(appId, type, userId, pageNo);
    }
}
