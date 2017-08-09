package com.handler;

import com.service.UserInfoService;
import com.service.impl.UserInfoServiceImpl;
import com.vo.UserInfo;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by luozi on 2017/7/20.
 */
public class UserInfoHandler extends MultiCommandHandler {
    private static Logger logger = Logger.getLogger(UserInfoHandler.class);
    static UserInfoService userInfoService = new UserInfoServiceImpl();

    /**
     * 发布动态
     */
    public void addUserInfo(Command cmd) {
        try {
            logger.info("-------publishDynamic-------cmd:" + cmd);
            //公共参数 start
            String loginId=cmd.getStringParam("loginId");
            String appId=cmd.getStringParam("appId");
            String authorId=cmd.getStringParam("authorId");
            String version=cmd.getStringParam("version");
            String IMEI=cmd.getStringParam("IMEI");
            String model=cmd.getStringParam("model");
            String platform=cmd.getStringParam("platform");
            //公共参数 end
            String headImg=null;
            UserInfo userInfo=new UserInfo();
            userInfo.setNickName(cmd.getStringParam("nickName"));
            userInfo.setType(cmd.getIntParam("type"));
            userInfo.setRegType(cmd.getIntParam("regType"));
            userInfo.setPwd(cmd.getStringParam("pwd"));
            userInfo.setSex(cmd.getIntParam("sex"));
            userInfo.setBirthday(cmd.getLongParam("birthday"));
            userInfo.setHeadImg(headImg);
            userInfo.setProvince(cmd.getStringParam("province"));
            userInfo.setCity(cmd.getStringParam("city"));
            userInfo.setRegion(cmd.getStringParam("region"));
            userInfo.setStar(cmd.getStringParam("star"));
            userInfo.setAuthorId(cmd.getStringParam("authorId"));
            userInfo.setWeiXin(cmd.getStringParam("weiXin"));
            userInfo.setQq(cmd.getStringParam("qq"));
            userInfo.setEnabled(cmd.getIntParam("enabled"));
            userInfoService.addUserInfo(userInfo);
            Response response = new Response();
            response.addValue("code", "0");
            response.addValue("info", "SUCCESS");
            cmd.setResponse(response);
        }catch (Exception ex){
            logger.error("addUserInfo----cmd:" + cmd + ",exception:", ex);
            Response response = new Response();
            response.addValue("code", "1");
            response.addValue("info", "注册用户失败");
            cmd.setResponse(response);
        }
    }

    /**
     * 根据ID查找用户
     * @param
     * @return
     */
    public void getUserInfoById (Command cmd) {

        String userId = cmd.getStringParam("userId");
        try {
            long id = 0;
            if(StringUtils.isNotEmpty(userId)) {
                id = Long.parseLong(userId);
            }
            logger.info("-------publishDynamic-------cmd:" + cmd);
            userInfoService.getUserInfoById(id);
            Response response = new Response();
            response.addValue("code", "0");
            response.addValue("info", "SUCCESS");
            cmd.setResponse(response);
        } catch (Exception e) {
            logger.error("addUserInfo----cmd:" + cmd + ",exception:", e);
            Response response = new Response();
            response.addValue("code", "1");
            response.addValue("info", "查询失败");
            cmd.setResponse(response);
            e.printStackTrace();
        }
    }
}
