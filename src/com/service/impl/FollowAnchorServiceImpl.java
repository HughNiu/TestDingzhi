package com.service.impl;

import com.dao.DaoFactory;
import com.dao.FollowDao;
import com.service.FollowAnchorService;
import com.util.CommonUtil;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class FollowAnchorServiceImpl implements FollowAnchorService {
	private static FollowDao followDao = new FollowDao();
	private static Logger logger = Logger.getLogger(FollowAnchorServiceImpl.class);
	private IRedisDao redisFollowDao = DaoFactory.getFollowRedisDao();

	/**
	 * 是否关注过
	 * 
	 * @param uid
	 *            用户ID
	 * @return boolean true已经关注，false未关注
	 */
	public boolean isFollow(long uid, long anchorId) throws Exception {
		if (uid > 0 && anchorId > 0) {
			String isFollowObj = redisFollowDao.get(CommonUtil.IS_FOLLOW + uid + "_" + anchorId);
			if (StringUtils.isNotBlank(isFollowObj)) {
				if (isFollowObj.equals("1")) {
					logger.info("-----isFollow---redis-uid:" + uid + ",anchorId:" + anchorId + ",isFollow:" + true);
					return true;
				} else {
					logger.info("-----isFollow--redis--uid:" + uid + ",anchorId:" + anchorId + ",isFollow:" + false);
					return false;
				}
			} else {
				Map<String, Object> map = followDao.getFollow(uid, anchorId);
				if (map != null && !map.isEmpty()) {
					redisFollowDao.set(CommonUtil.IS_FOLLOW + uid + "_" + anchorId, "1",
							CommonUtil.USER_REDIS_KEY_TIME);
					logger.info("-----isFollow--db--uid:" + uid + ",anchorId:" + anchorId + ",isFollow:" + true);

					return true;
				} else {
					redisFollowDao.set(CommonUtil.IS_FOLLOW + uid + "_" + anchorId, "0",
							CommonUtil.USER_REDIS_KEY_TIME);
					logger.info("-----isFollow--db--uid:" + uid + ",anchorId:" + anchorId + ",isFollow:" + false);
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 添加关注
	 * 
	 * @param uid 用户id
	 *            long
	 * @param anchorId 主播id（多个主播id用逗号分割）
	 *            String
	 */
	public boolean addFollow(long uid, long anchorId) throws Exception {
		boolean isFollow = false;
		if (uid > 0 && anchorId > 0) {
			isFollow = isFollow(uid, anchorId);
			if (isFollow == false) {
				followDao.addFollow(uid, anchorId);
				redisFollowDao.hinc(CommonUtil.USER_KEY + uid, CommonUtil.FOLLOW_NUMBER, 1);
				redisFollowDao.hinc(CommonUtil.USER_ANCHOR_KEY + anchorId, CommonUtil.ANCHOR_FANS_NUMBER, 1);
				redisFollowDao.set(CommonUtil.IS_FOLLOW + uid + "_" + anchorId, "1",
						CommonUtil.USER_REDIS_KEY_TIME * 2);
				redisFollowDao.del(CommonUtil.FOLLOW_LIST + uid);
			}
		}
		logger.info("-----addFollow----uid:" + uid + ",anchorId:" + anchorId + ",isFollow:" + isFollow);
		return true;
	}

	/**
	 * 我的粉丝列表
	 */
	public List<Map<String, Object>> queryFans(long anchorId, int page, long timestamp) throws Exception {
		List<Map<String, Object>> lstData = followDao.queryFans(anchorId, page, timestamp);
 		logger.info("-----queryFans----anchorId:" + anchorId + ",page:" + page + ",timestamp:" + timestamp + ",lstData:"
				+ lstData);
		return lstData;
	}

	/**
	 * 我的粉丝数量
	 */
	public int getFansNumber(long anchorId) throws Exception {
		String fanObj = redisFollowDao.hget(CommonUtil.USER_ANCHOR_KEY + anchorId, CommonUtil.ANCHOR_FANS_NUMBER);
		int num = 0;
		if (StringUtils.isBlank(fanObj)) {
			num = followDao.getAnchorFansNumber(anchorId);
			redisFollowDao.hinc(CommonUtil.USER_ANCHOR_KEY + anchorId, CommonUtil.ANCHOR_FANS_NUMBER, num);
		}
		logger.info("---getFansNumber----anchorId:" + anchorId + ",fanObj:" + fanObj + ",numDb:" + num);
		return StringUtils.isNotBlank(fanObj) ? Integer.valueOf(fanObj) : num;
	}

}
