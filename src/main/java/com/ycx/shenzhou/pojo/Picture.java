package com.ycx.shenzhou.pojo;

public class Picture {
    private String id; //图片ID
    private String url; //存放路径
    private String positiontype; //所属位置类型 含义未定
    private String specificposition; //所属具体位置 含义未定

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPositiontype() {
        return positiontype;
    }

    public void setPositiontype(String positiontype) {
        this.positiontype = positiontype;
    }

    public String getSpecificposition() {
        return specificposition;
    }

    public void setSpecificposition(String specificposition) {
        this.specificposition = specificposition;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", positiontype='" + positiontype + '\'' +
                ", specificposition='" + specificposition + '\'' +
                '}';
    }
}