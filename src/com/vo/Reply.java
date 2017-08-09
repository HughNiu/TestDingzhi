package com.vo;

/**
 * 回复
 * Created by 云瑞 on 2017/8/7.
 */
public class Reply extends BaseEntity {

    private int id;//		主键自增
    private String comtent;//回复内容
    private Long creadTime;//创建时间，时间戳，用于排序
    private int status;//是否删除  1 删除   0可用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComtent() {
        return comtent;
    }

    public void setComtent(String comtent) {
        this.comtent = comtent;
    }

    public Long getCreadTime() {
        return creadTime;
    }

    public void setCreadTime(Long creadTime) {
        this.creadTime = creadTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", comtent='" + comtent + '\'' +
                ", creadTime=" + creadTime +
                ", status=" + status +
                '}';
    }
}
