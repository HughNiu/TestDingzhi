package com.common;

/**
 * Created by Niu Qianghong on 2017-08-09 0009.
 */
public class Constants {

    /**
     * 通知相关
     */
    public static final int NOTIFY_SYS = 0; // 系统通知类型

    public static final int NOTIFY_POST_REPLY = 1; // 帖子回复通知类型

    public static final int NOTIFY_CMT_REPLY = 2; // 评论回复通知类型

    public static final int NOTIFY_BOOK_CMT = 3; // 图书评论通知类型

    public static final int NOTIFY_REWARD_ARTICLE = 4; // 文章打赏通知类型

    public static final int NOTIFY_REWARD_CHAPTER = 5; // 章节打赏通知类型

    public static final int NOTIFY_UNREAD = 0; // 通知未读状态


    /**
     * 分页相关
     */
    public static final int PAGE_SIZE = 20; //每页展示数量


    /**
     * 交易表相关
     */
    public static final int TRANS_RECHARGE = 0; // 交易类型-充值

    public static final int TRANS_REWARD_ARTICLE = 1; // 交易类型-文章打赏

    public static final int TRANS_REWARD_CHAPTER = 2; // 交易类型-图书章节打赏

    public static final int TRANS_SUBSCRIBE = 3; // 交易类型-图书订阅

    public static final int TRANS_GIFT = 4; // 交易类型-直播送礼


    /**
     * 支付相关
     */
    public static final int PAY_TYPE_DIAMOND = 0; // 钻石支付类型

    public static final int PAY_TYPE_WECHAT = 1; // 微信支付类型

    public static final int PAY_TYPE_ALIPAY = 2; // 支付宝支付类型


    /**
     * 用户相关
     */
    public static final int USER_TYPE_NORMAL = 0; // 普通用户类型

    public static final int USER_TYPE_ADMIN = 1; // 管理员用户类型

    public static final int USER_TYPE_AUTHOR = 2; // 作者用户类型

    /**
     * 通知来源相关
     */
    public static final int SOURCE_TYPE_POST = 0; // 来源为帖子

    public static final int SOURCE_TYPE_CMT = 1; // 来源为评论

    public static final int SOURCE_TYPE_BOOK = 2; // 来源为图书

    public static final int SOURCE_TYPE_ARTICLE= 3; // 来源为文章
}
