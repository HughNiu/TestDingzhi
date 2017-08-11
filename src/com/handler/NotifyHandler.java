package com.handler;

import com.service.NotifyService;
import com.service.impl.NotifyServiceImpl;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-11 0011.
 */
public class NotifyHandler extends MultiCommandHandler {

    private Logger logger = Logger.getLogger(this.getClass());

    private NotifyService notifyService = new NotifyServiceImpl();

    /**
     * 根据用户id获取未读通知
     * @param cmd
     */
    public void getSummary(Command cmd){
        long userId = cmd.getLongParam("userId");
        Map<String, Object> unreadNotify = notifyService.getUnread(userId);
        Response response = new Response();
        response.addValue("status", "200");
        response.addValue("data", unreadNotify);
        cmd.setResponse(response);
    }

    /**
     * 获取通知列表
     * type = 0, 系统通知; = 1, 回复; = 2, 评论; = 3, 打赏
     * @param cmd
     */
    public void getList(Command cmd) {
        long userId = cmd.getLongParam("userId");
        int pageNo = cmd.getIntParam("pageNo");
        int type = cmd.getIntParam("type");
        List<Map<String, Object>> notifyList = null;
        try {
            notifyList = notifyService.getList(userId, type, pageNo);
        } catch (Exception e) {
            logger.error("NotifyHandler: 获取通知列表哦失败", e);
        }
        Response response = new Response();
        response.addValue("status", "200");
        response.addValue("data", notifyList);
        cmd.setResponse(response);
    }
}
