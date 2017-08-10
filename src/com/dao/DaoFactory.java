package com.dao;

import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;

import com.LocalResourceManager;
import com.zw.zcf.dao.mongo.IMongoDao;
import com.zw.zcf.dao.mongo.factory.MongoDaoFactory;
import com.zw.zcf.dao.redis.IRedisDao;
import com.zw.zcf.dao.redis.factory.RedisDaoFactory;

/**
 * Dao工厂
 */
public class DaoFactory {
	private static Logger logger = Logger.getLogger(DaoFactory.class);

//	@Autowired
//	public  static ExtendProperties extendProperties;

	public static  IMongoDao getFollowMongoDao(String db, String table) {
		MongoDaoFactory.warnLogTimeThreshold = 1000;
		String mongoHost=LocalResourceManager.getProperty("dingzhi.mongoHost");
 		logger.info("----getFollowMongoDao-----mongoHost:"+mongoHost);
		return MongoDaoFactory.getMongoDao(mongoHost, db, table, "", "");
	}

	public static  IMongoDao getMongoDao(String db, String table) {
		MongoDaoFactory.warnLogTimeThreshold = 1000;
		String mongoHost=LocalResourceManager.getProperty("dingzhi.mongoHost");
		logger.info("----getFollowMongoDao-----mongoHost:"+mongoHost);
		return MongoDaoFactory.getMongoDao(mongoHost, db, table, "", "");
	}

	public static IRedisDao getFollowRedisDao() {
		String server=LocalResourceManager.getProperty("dingzhi.redis.server");
		String port=LocalResourceManager.getProperty("dingzhi.redis.port");

		logger.info("----getFollowRedisDao-----server:"+server+",port:"+port);
		return RedisDaoFactory.getRedisDao(server,
				Integer.valueOf(port),"");
	}

	/**
	 * add by luozi
	 * @return
	 */
	public static IRedisDao getRedisDao() {
		String server=LocalResourceManager.getProperty("dingzhi.redis.server");
		String port=LocalResourceManager.getProperty("dingzhi.redis.port");

		logger.info("----getFollowRedisDao-----server:"+server+",port:"+port);
		return RedisDaoFactory.getRedisDao(server,
				Integer.valueOf(port),"");
	}
}
