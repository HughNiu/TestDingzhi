package com.vo;


/**
 * 暂时不用这个 类
 * 产品类(1 公告，2文章，3帖子，4动态，5直播)
 * Created by luozi on 2017/7/20.
 */
public class Product extends BaseEntity {
    private String id;//主键
    private int type;//1 公告，2文章，3帖子，4动态，5直播
    private String imgUrls;//多个图片，逗号分开
    private String content;//内容
    private String publishUserId;//发布人id
    private long noticeStartDate;//公告开始时间
    private long noticeEndDate;//公告结束时间
    private int noticeType;//公告发布类型
    private String noticeUrl;//公告跳转URL
    private String tilte;//文章标题
    private String cover;//文章标题
    private long recommendDate;//帖子的推荐时间

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

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public long getNoticeStartDate() {
        return noticeStartDate;
    }

    public void setNoticeStartDate(long noticeStartDate) {
        this.noticeStartDate = noticeStartDate;
    }

    public long getNoticeEndDate() {
        return noticeEndDate;
    }

    public void setNoticeEndDate(long noticeEndDate) {
        this.noticeEndDate = noticeEndDate;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(long recommendDate) {
        this.recommendDate = recommendDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", imgUrls='" + imgUrls + '\'' +
                ", content='" + content + '\'' +
                ", publishUserId='" + publishUserId + '\'' +
                ", noticeStartDate=" + noticeStartDate +
                ", noticeEndDate=" + noticeEndDate +
                ", noticeType=" + noticeType +
                ", noticeUrl='" + noticeUrl + '\'' +
                ", tilte='" + tilte + '\'' +
                ", cover='" + cover + '\'' +
                ", recommendDate=" + recommendDate +
                '}';
    }
}
