package com.handler;


import com.service.NewsReadService;
import com.service.impl.NewsReadServiceImpl;
import com.util.check.ResponseResult;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 最近阅读
 */
public class NewsReadHandler extends MultiCommandHandler {
	private static Logger logger = Logger.getLogger(NewsReadHandler.class);

	static NewsReadService newsReadService = new NewsReadServiceImpl();

	/**
	 * 获取作品
	 *
	 * @param cmd
	 */
	public void getNewsRead(Command cmd) {
		try {

			List<Map<String, List>> bookList = new ArrayList<Map<String, List>>();

			logger.info("-------getNewsRead-------cmd:" + cmd);

			Response response = new Response();

			String userId = cmd.getStringParam("userId");
			int pageNo = cmd.getIntParam("pageNo");

			//判断参数是否存在
			if (StringUtils.isBlank(userId) || StringUtils.isBlank(pageNo + "")) {
				response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.LOST_PARAM.getCode());
				response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.LOST_PARAM.getInfo());
				cmd.setResponse(response);
				return;
			}

			//获取最近阅读
			Map<String, Object> newsReadMap = newsReadService.getNewsRead(userId, pageNo);

			response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.SUCCESS.getCode());
			response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.SUCCESS.getInfo());
			response.addValue(ResponseResult.DATA, newsReadMap);
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("getNewsRead----cmd:" + cmd + ",exception:", ex);
		}
	}


}
