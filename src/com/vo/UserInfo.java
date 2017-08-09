package com.vo;

/**
 * 用户信息
 * Created by luozi on 2017/7/20.
 */
public class UserInfo extends BaseEntity {
    private String id;//id 作者ID
    private String nickName;//作者昵称
    private int type;//1:作者，2：读者，3：admin
    private int regType;//1：微信，2 ：QQ
    private String pwd;//第三方注册的密码
    private int sex;//1:男，2：女
    private long birthday;//生日
    private String headImg;//头像
    private String province;//省
    private String city;//市
    private String region;//区
    private String star;//星座
    private String authorId;//资源库的作者ID 1:1关系
    private String weiXin;//微信号
    private String qq;//QQ
    private int enabled;//1启用，0禁言
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRegType() {
        return regType;
    }

    public void setRegType(int regType) {
        this.regType = regType;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", type='" + type + '\'' +
                ", regType=" + regType +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", headImg='" + headImg + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", star='" + star + '\'' +
                ", authorId='" + authorId + '\'' +
                ", weiXin='" + weiXin + '\'' +
                ", qq='" + qq + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
