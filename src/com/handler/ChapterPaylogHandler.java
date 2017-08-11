package com.handler;


import com.service.BookService;
import com.service.ChapterPaylogService;
import com.service.impl.BookeServiceImpl;
import com.service.impl.ChapterPaylogServiceImpl;
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
 * 章节购买记录
 */
public class ChapterPaylogHandler extends MultiCommandHandler {
	private static Logger logger = Logger.getLogger(ChapterPaylogHandler.class);

	static ChapterPaylogService chapterPaylogService = new ChapterPaylogServiceImpl();

	/**
	 * 判断用户是否已购买
	 * @param cmd
	 */
	public void ChapterPaylogBuy(Command cmd) {
		try {

			List<Map<String, List>> ChapterPaylogList = new ArrayList<Map<String, List>>();

			logger.info("-------ChapterPaylogBuy-------cmd:" + cmd);

			Response response = new Response();

			String userId = cmd.getStringParam("userId");
			String bookId = cmd.getStringParam("bookId");
			String chapterId = cmd.getStringParam("chapterId");

			//判断参数是否存在
			if (StringUtils.isBlank(chapterId)||StringUtils.isBlank(userId)||StringUtils.isBlank(bookId)) {
				response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.LOST_PARAM.getCode());
				response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.LOST_PARAM.getInfo());
				cmd.setResponse(response);
				return;
			}

			//获取图书列表
			boolean flag = chapterPaylogService.ChapterPaylogBuy(userId, bookId,chapterId);

			response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.SUCCESS.getCode());
			response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.SUCCESS.getInfo());
			response.addValue(ResponseResult.DATA, flag);
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("ChapterPaylogBuy----cmd:" + cmd + ",exception:", ex);
		}
	}

}
