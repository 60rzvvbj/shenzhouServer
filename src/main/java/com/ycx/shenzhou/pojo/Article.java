package com.ycx.shenzhou.pojo;

public class Article {
    private String id; // 文章编号
    private String title; // 文章标题
    private String placeName; // 景点名
    private String province; // 景点所在省份
    private long releaseTime; // 发布时间
    private String content; // 文章内容
    private String account; // 作者账号
    private int thumb; // 点赞数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", placeName='" + placeName + '\'' +
                ", province='" + province + '\'' +
                ", releaseTime=" + releaseTime +
                ", content='" + content + '\'' +
                ", account='" + account + '\'' +
                ", thumb=" + thumb +
                '}';
    }
}