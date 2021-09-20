package com.ycx.shenzhou.pojo;

public class Picture {
    private String id; // 图片ID
    private String url; // 存放路径
    private int positionType; // 所属位置类型 含义未定
    private int specificPosition; // 所属具体位置 含义未定

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

    public int getPositionType() {
        return positionType;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public int getSpecificPosition() {
        return specificPosition;
    }

    public void setSpecificPosition(int specificPosition) {
        this.specificPosition = specificPosition;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", positionType=" + positionType +
                ", specificPosition=" + specificPosition +
                '}';
    }
}