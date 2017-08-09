package com.vo;

import java.io.Serializable;

/**
 * 基础公共类
 * Created by luozi on 2017/7/20.
 */
public class BaseEntity implements Serializable{
    private String appid;//appid
    private long createDate;//创建日期
    private String createUserID;//创建人id
    private long latestDate;//修改日期
    private String modifyUserId;//修改人id

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }

    public long getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(long latestDate) {
        this.latestDate = latestDate;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

}
