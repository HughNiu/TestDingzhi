package com.util;

/**
 * 公用操作类
 *
 * @author tangyong
 */
public interface CommonUtil {
	
	public static final String USER_KEY="dz_u_i_";//用户基本信息
	final public static String FOLLOW_NUMBER = "dz_l_o_";// 关注数 field

	
	public static final String USER_ANCHOR_KEY="dz_a_l_";//主播基本信息  dz_a_i_1633508
	final public static String ANCHOR_FANS_NUMBER = "dz_l_f_";//主播粉丝数 field
	
	final public static String IS_FOLLOW = "dz_l_i_";// 是否关注
	final public static String FOLLOW_LIST = "dz_l_i_";// 关注的列表
	final public static String ALL_ANCHOR = "dz_all_anchors";// 所有的主播

	final public static String TOP_ANCHOR_OPEN_NOW = "dz_F_o_n_";//正在直播的主播
	final public static String TOP_ANCHOR_NOT_OPEN_NOW = "dz_F_o_no_";//未开播的主播

	final public static String TOP_ANCHOR_MONEY = "dz_m_n_";//收入最高的主播
	final public static Long MAX_VALUE = Long.MAX_VALUE;//收入最高的主播

	final public static int USER_REDIS_KEY_TIME = 60 * 60 * 24 * 7;
}
