package com;

import com.handler.*;
import org.apache.log4j.Logger;

import com.zw.zcf.command.executor.SimpleCommandExecutor;
import com.zw.zcf.server.HttpServer;

/**
 * BootstrapServer
 */
public class BootstrapDingzhiServer {
 	private static Logger logger = Logger.getLogger(BootstrapDingzhiServer.class);
	private static String httpServerHost=LocalResourceManager.getProperty("httpServerHost");
	private static String httpServerPort=LocalResourceManager.getProperty("httpServerPort");

	public static void main(String[] args) throws Exception {
		SimpleCommandExecutor httpExecutor = new SimpleCommandExecutor();
		// 注册handler
		httpExecutor.registerRegexHandler("follow", new FollowHandler());
        httpExecutor.registerHandler("product",new ProductHandler());
		httpExecutor.registerHandler("userInfo",new UserInfoHandler());
		httpExecutor.registerHandler("homaPage",new HomePageHandler());
		httpExecutor.registerHandler("comment",new CommentHandler());
		httpExecutor.registerHandler("book",new BookHandler());
		httpExecutor.registerHandler("trans",new TransInfoHandler());
		httpExecutor.registerHandler("notify",new NotifyHandler());
		httpExecutor.registerHandler("chapterPaylog",new ChapterPaylogHandler());
		httpExecutor.registerHandler("newsRead",new NewsReadHandler());


		// 启动一个HTTP服务器，默认是netty
		new HttpServer(httpServerHost, Integer.valueOf(httpServerPort), httpExecutor).start();
		System.out.println(httpServerHost+"---service started!!!");
	}
	// 添加：http://127.0.0.1:5081/follow?m=addFollow&uid=100&anchorId=200
	// 查询多条：http://127.0.0.1:5081/follow?m=queryAnchorFollow&anchorId=200&page=1&timestamp=0

	// 删除：http://127.0.0.1:7081/test?m=del&id=1
	// 修改：http://127.0.0.1:7081/test?m=update&id=1&newId=2
	// 查询单条：http://127.0.0.1:7081/test?m=getOne&id=1
}
