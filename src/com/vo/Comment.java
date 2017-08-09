package com.vo;

import java.util.List;

/**
 * 评论
 * Created by 云瑞 on 2017/8/7.
 */
public class Comment extends BaseEntity {

    private String id;     //主键
    private int type;      //类型 1.图书  2.文章  3.回复 4.贴子 5.动态
    private int accentId;  //被评论的ID  {帖子ID，动态ID，章节ID 图书id }
    private int status;    // 是否删除  1 删除   0可用;
    private String content; //评论内容

    private List<Reply> replyList; //回复内容

    private long createTime;//公告开始时间
    private long updateTime;//公告结束时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAccentId() {
        return accentId;
    }

    public void setAccentId(int accentId) {
        this.accentId = accentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", accentId=" + accentId +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", replyList=" + replyList +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
