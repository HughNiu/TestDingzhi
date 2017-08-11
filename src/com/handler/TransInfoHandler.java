package com.handler;

import com.common.Constants;
import com.service.TransInfoService;
import com.service.impl.TransInfoServiceImpl;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Niu Qianghong on 2017-08-11 0011.
 */
public class TransInfoHandler extends MultiCommandHandler {

    private TransInfoService transInfoService = new TransInfoServiceImpl();

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 获取交易记录
     * @param cmd
     */
    public void getTransList(Command cmd){
        try {
            long userId = cmd.getLongParam("userId");
            int type = cmd.getIntParam("type"); // type = 0, 查询充值记录; type = 1, 查询消费记录
            int pageNo = cmd.getIntParam("pageNo");
            List<Integer> typeList = new ArrayList<Integer>();
            if (type == 0) {
                typeList.add(Constants.TRANS_RECHARGE);
            } else {
                typeList.add(Constants.TRANS_REWARD_ARTICLE);
                typeList.add(Constants.TRANS_REWARD_CHAPTER);
                typeList.add(Constants.TRANS_SUBSCRIBE);
                typeList.add(Constants.TRANS_GIFT);
            }

            List<Map<String, Object>> transList = transInfoService.getTransList(userId, typeList, pageNo);
            Response response = new Response();
            response.addValue("status", "200");
            response.addValue("data", transList);
            cmd.setResponse(response);
        } catch (Exception e) {
            logger.error("TransInfoHandler: 获取交易记录发生异常", e);
        }
    }

}
