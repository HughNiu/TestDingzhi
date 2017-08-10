package com.handler;


import com.service.BookService;
import com.service.impl.BookeServiceImpl;
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
 * 作品页
 */
public class BookHandler extends MultiCommandHandler {
	private static Logger logger = Logger.getLogger(BookHandler.class);

	static BookService bookService = new BookeServiceImpl();

	/**
	 * 获取作品
	 *
	 * @param cmd
	 */
	public void getBookData(Command cmd) {
		try {

			List<Map<String, List>> bookList = new ArrayList<Map<String, List>>();

			logger.info("-------bookeList-------cmd:" + cmd);

			Response response = new Response();

			String appId = cmd.getStringParam("appId");
			int pageNo = cmd.getIntParam("pageNo");

			//判断参数是否存在
			if (StringUtils.isBlank(appId) || StringUtils.isBlank(pageNo + "")) {
				response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.LOST_PARAM.getCode());
				response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.LOST_PARAM.getInfo());
				cmd.setResponse(response);
				return;
			}

			//获取图书列表
			Map<String, Object> bookMap = bookService.getBookData(appId, pageNo);

			response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.SUCCESS.getCode());
			response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.SUCCESS.getInfo());
			response.addValue(ResponseResult.DATA, bookMap);
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("getBookData----cmd:" + cmd + ",exception:", ex);
		}
	}

	/**
	 * 获取图书对应的目录
	 *
	 * @param cmd
	 */
	public void getBookChapter(Command cmd) {
		try {

			List<Map<String, List>> bookList = new ArrayList<Map<String, List>>();

			logger.info("-------getBookChapter-------cmd:" + cmd);

			Response response = new Response();

			String bookId = cmd.getStringParam("bookId");
			String userId = cmd.getStringParam("userId");

			//判断参数是否存在
			if (StringUtils.isBlank(bookId)||StringUtils.isBlank(userId)) {
				response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.LOST_PARAM.getCode());
				response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.LOST_PARAM.getInfo());
				cmd.setResponse(response);
				return;
			}

			//获取图书目录
			List<Map<String, Object>> bookMap = bookService.getBookChapter(bookId,userId);

			response.addValue(ResponseResult.CODE, ResponseResult.ResultMsg.SUCCESS.getCode());
			response.addValue(ResponseResult.INFO, ResponseResult.ResultMsg.SUCCESS.getInfo());
			response.addValue(ResponseResult.DATA, bookMap);
			cmd.setResponse(response);
		} catch (Exception ex) {
			logger.error("getBookChapter----cmd:" + cmd + ",exception:", ex);
		}
	}
}
