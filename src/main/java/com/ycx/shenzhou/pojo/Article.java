package com.ycx.shenzhou.pojo;

public class Article {
    private String id; //文章编号
    private String title; //文章标题
    private String placename; //景点名
    private String province; //景点所在省份
    private Long releasetime; //发布时间
    private String content; //文章内容
    private String account; //作者账号

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

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(long releasetime) {
        this.releasetime = releasetime;
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

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", placename='" + placename + '\'' +
                ", province='" + province + '\'' +
                ", releasetime=" + releasetime +
                ", content='" + content + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}