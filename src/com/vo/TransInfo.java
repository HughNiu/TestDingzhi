package com.vo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-10 0010.
 */
public class TransInfo {
    private Long transId; // id
    private Long orderId; // 订单id
    private Integer type; // 类型
    private Long goodsId; // 物品id
    private Integer goodsNum; // 物品数量
    private Integer amount; // 金额
    private Integer discount; // 打折
    private Integer discountMoney; // 打折金额
    private Integer payType; // 支付类型
    private Long userId; // 消费者id
    private Long targetId; // 收益者id
    private String appId; // 应用标识
    private Long createTime;
    private Long timestamp;

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> transInfo = new HashMap<String, Object>();
        transInfo.put("transId", transId);
        transInfo.put("orderId", orderId);
        transInfo.put("type", type);
        transInfo.put("goodsId", goodsId);
        transInfo.put("goodsNum", goodsNum);
        transInfo.put("amount", amount);
        transInfo.put("discount", discount);
        transInfo.put("discountMoney", discountMoney);
        transInfo.put("payType", payType);
        transInfo.put("userId", userId);
        transInfo.put("targetId", targetId);
        transInfo.put("appId", appId);
        transInfo.put("createTime", createTime);
        transInfo.put("timestamp", timestamp);
        return transInfo;
    }
}
