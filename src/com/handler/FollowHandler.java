package com.handler;

import com.service.FollowAnchorService;
import com.service.impl.FollowAnchorServiceImpl;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * 关注管理
 * 
 * @author tangyong
 * 
 */
public class FollowHandler extends MultiCommandHandler {
 	private static Logger logger = Logger.getLogger(FollowHandler.class);

	static FollowAnchorService followAnchorService=new FollowAnchorServiceImpl();
	/**
	 * 添加关注
	 */
	public void addFollow(Command cmd) {
		try {
			logger.info("-------addFollow-------cmd:" + cmd);

			long uid = cmd.getLongParam("uid");
			long anchorId = cmd.getLongParam("anchorId");

 			followAnchorService.addFollow(uid,anchorId);

			Response response = new Response();
			response.addValue("status", "200");
			response.addValue("msg", "关注成功");
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("addFollow----cmd:" +cmd + ",exception:", ex);
		}
	}
	/**
	 * 查询主播的粉丝
	 */
	public void queryAnchorFollow(Command cmd) {
		try {
			logger.info("-------queryAnchorFollow-------cmd:" + cmd);
			long anchorId = cmd.getLongParam("anchorId");
			int page = cmd.getIntParam("page");
			long timestamp = cmd.getLongParam("timestamp");
			List<Map<String, Object>> lst=followAnchorService.queryFans(anchorId,page,timestamp);
			Response response = new Response();
			response.addValue("status", "200");
			response.addValue("data",lst);
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("queryAnchorFollow----cmd:" + cmd + ",exception:", ex);
		}
	}

}
