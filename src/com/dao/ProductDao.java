package com.dao;

import com.vo.Product;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luozi on 2017/7/20.
 */
public class ProductDao {
    private final IMongoDao mongoDao;
    public ProductDao() {
        mongoDao = DaoFactory.getFollowMongoDao("dingzhidb","Product");
    }
    /**
     * 添加产品
     * @param product 产品vo
     */
    public void addProduct(Product product) throws Exception {
        Map<String,Object> record=new HashMap<String,Object>();
        record.put("id", mongoDao.getAutoId("id"));
        record.put("type",product.getType());
        record.put("imgUrls",product.getImgUrls());
        record.put("content",product.getContent());
        record.put("publishUserId",product.getPublishUserId());
        record.put("noticeStartDate",product.getNoticeEndDate());
        record.put("noticeEndDate",product.getNoticeEndDate());
        record.put("noticeType",product.getNoticeType());
        record.put("noticeUrl",product.getNoticeUrl());
        record.put("tilte",product.getTilte());
        record.put("cover",product.getCover());
        record.put("recommendDate",product.getRecommendDate());
        record.put("appid",product.getAppid());
        record.put("createDate",product.getCreateDate());
        record.put("createUserID",product.getCreateUserID());
        record.put("latestDate",product.getLatestDate());
        record.put("modifyUserId",product.getModifyUserId());
        record.put("appid",product.getAppid());
        record.put("createDate",product.getCreateDate());
        record.put("createUserID",product.getCreateUserID());
        record.put("latestDate",product.getLatestDate());
        mongoDao.insert(record);
    }
}
