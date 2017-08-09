package com.service;

import java.util.List;
import java.util.Map;

public interface FollowAnchorService {
	/**
	 * 是否关注过
	 *
	 * @param uid 用户ID
	 * @return boolean true已经关注，false未关注
	 */
	public boolean isFollow(long uid, long anchorId) throws Exception;

	/**
	 * 添加关注
	 *
	 * @param uid      用户id
	 *                 long
	 * @param anchorId 主播id（多个主播id用逗号分割）
	 *                 String
	 */
	public boolean addFollow(long uid, long anchorId) throws Exception;

	/**
	 * 我的粉丝列表
	 */
	public List<Map<String, Object>> queryFans(long anchorId, int page, long timestamp) throws Exception;

	/**
	 * 我的粉丝数量
	 */
	public int getFansNumber(long anchorId) throws Exception;
}