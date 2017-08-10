package com.handler;

import com.service.HomePageService;
import com.service.impl.HomePageServiceImpl;
import com.util.check.ResponseResult;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.zw.zcf.queue.*;

/**
 * 首页管理
 */
public class HomePageHandler extends MultiCommandHandler {
 	private static Logger logger = Logger.getLogger(HomePageHandler.class);

	static HomePageService homePageService=new HomePageServiceImpl();

	/**
	 * 获取首页数据
	 * @param cmd
	 */
	public void getHomePageData(Command cmd) {
		try {

			List<Map<String,List>> homePageList = new ArrayList<Map<String,List>>();

			logger.info("-------getHomePageData-------cmd:" + cmd);

			Response response = new Response();

			String uid = cmd.getStringParam("userId");
			String appid = cmd.getStringParam("appid");
			int pageNo = cmd.getIntParam("pageNo");
			long timestamp = cmd.getLongParam("timestamp");

			//判断参数是否存在
			if(StringUtils.isBlank(uid)||StringUtils.isBlank(appid)||
					StringUtils.isBlank(pageNo+"")||StringUtils.isBlank(timestamp+"")){
				response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.LOST_PARAM.getCode());
				response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.LOST_PARAM.getInfo());
				cmd.setResponse(response);
				return;
			}

			//获取首页公告
			Map<String,List> homePageMap = homePageService.getHomePageData(uid,appid,pageNo,timestamp);

			homePageList.add(homePageMap);

			response.addValue(ResponseResult.CODE,ResponseResult.ResultMsg.SUCCESS.getCode());
			response.addValue(ResponseResult.INFO,ResponseResult.ResultMsg.SUCCESS.getInfo());
			response.addValue(ResponseResult.DATA,homePageList);
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("getHomePageData----cmd:" +cmd + ",exception:", ex);
		}
	}
}
