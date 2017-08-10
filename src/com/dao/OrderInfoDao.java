package com.dao;

import com.zw.zcf.dao.mongo.IMongoDao;

/**
 * Created by Niu Qianghong on 2017-08-10 0010.
 */
public class OrderInfoDao {
    private IMongoDao mongoDao;
    public OrderInfoDao() {
        mongoDao = DaoFactory.getMongoDao("dingzhidb", "cust_order_info");
    }
}
