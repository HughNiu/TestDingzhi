package com.vo;

/**
 * 公告，文章，帖子，动态
 * Created by 云瑞 on 2017/8/7.
 */
public class Fforum extends BaseEntity {

    private int id; //主键ID
    private long userId;//用户ID
    private String coverImg;//封面
    private String commentTitle;//标题
    private String commentComtent;//内容
    private String commentImg;//配图
    private long readCount;//阅读数
    private String jumpUrl;//跳转url
    private int type; // 类型 1 帖子  2 动态  3 文章  4 公告
    private int status; //状态 0  正常    1 删出
    private long startTime;//开始时间	用于公告
    private long endTime; //结束时间	用于公告
    private long createTime;	//创建时间
    private long updateTtime;//修改时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentComtent() {
        return commentComtent;
    }

    public void setCommentComtent(String commentComtent) {
        this.commentComtent = commentComtent;
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }

    public long getReadCount() {
        return readCount;
    }

    public void setReadCount(long readCount) {
        this.readCount = readCount;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTtime() {
        return updateTtime;
    }

    public void setUpdateTtime(long updateTtime) {
        this.updateTtime = updateTtime;
    }

    @Override
    public String toString() {
        return "Fforum{" +
                "id=" + id +
                ", userId=" + userId +
                ", coverImg='" + coverImg + '\'' +
                ", commentTitle='" + commentTitle + '\'' +
                ", commentComtent='" + commentComtent + '\'' +
                ", commentImg='" + commentImg + '\'' +
                ", readCount=" + readCount +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTtime=" + updateTtime +
                '}';
    }
}
