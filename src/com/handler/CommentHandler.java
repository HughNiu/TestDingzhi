package com.handler;

import com.service.CommentService;
import com.service.impl.CommentServiceImpl;
import com.util.check.ResponseResult;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评论回复
 * Created by 云瑞 on 2017/8/7.
 */
public class CommentHandler extends MultiCommandHandler {
    private static Logger logger = Logger.getLogger(ProductHandler.class);
    static CommentService commentService = new CommentServiceImpl();

    /**
     * 评论回复查询
     * @param cmd
     */
    public void findCommentList(Command cmd) {
        logger.info("-------findCommentList-------cmd:" + cmd);
        String appId = cmd.getStringParam("appId");
        int type = cmd.getIntParam("type");
        int pageNo = cmd.getIntParam("pageNo");
        long timestamp = cmd.getLongParam("timestamp");

        try {
            Map<String, Object> commentList = commentService.findCommentList(appId, type, pageNo,timestamp);
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            response.addValue(ResponseResult.DATA,commentList);
            cmd.setResponse(response);
        }catch (Exception ex) {
            logger.error("findCommentList----cmd:" + cmd + ",exception:", ex);
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }
    }

    /**
     * 评论回复添加
     */
    public void addComment (Command cmd) {
        logger.info("-------addComment-------cmd:" + cmd);
        String appId = cmd.getStringParam("appId");
        int type = cmd.getIntParam("type");
        long userId = cmd.getLongParam("userId");
        long accentId = cmd.getLongParam("accentId");
        String comtent = cmd.getStringParam("comtent");
        try {
            commentService.addComment(appId,type,userId,accentId,comtent);
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }
    }

    /**
     * 删除评论
     * @param cmd
     */
    public void deleteComment (Command cmd) {
        String appId = cmd.getStringParam("appId");
        long id = cmd.getLongParam("id");
        try {
            commentService.deleteComment(appId,id);
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }catch (Exception ex) {
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }
    }

    /**
     * 删除回复
     * @param cmd
     */
    public void deleteReply (Command cmd) {
        String appId = cmd.getStringParam("appId");
        long id = cmd.getLongParam("id");
        int commentId = cmd.getIntParam("commentId");
        try {
            commentService.deleteReply(appId,id,commentId);
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }catch (Exception ex) {
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }
    }

    /**
     * 我发布的回复
     */
    public void getMyReply(Command cmd) {
        String appId = cmd.getStringParam("appId");
        long userId = cmd.getLongParam("userId");
        int pageNo = cmd.getIntParam("pageNo");
        try {
            Map<String, Object> myReply = commentService.getMyReply(pageNo, userId, appId);
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
            response.addValue(ResponseResult.DATA,myReply);
            cmd.setResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            Response response = new Response();
            response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.FAIL.getCode());
            response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.FAIL.getInfo());
            response.addValue(ResponseResult.DATA,new String[]{});
            cmd.setResponse(response);
        }
    }
}
