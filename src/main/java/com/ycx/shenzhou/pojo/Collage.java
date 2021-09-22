package com.ycx.shenzhou.pojo;

public class Collage {
    private String id; // 拼团ID
    private String account; // 发起人账号
    private int pNumber; // 总人数
    private String departure; // 起点
    private String destination; //终点
    private long dTime; //出发时间
    private String describe; //具体信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getdTime() {
        return dTime;
    }

    public void setdTime(long dTime) {
        this.dTime = dTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Collage{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", pNumber=" + pNumber +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", dTime=" + dTime +
                ", describe='" + describe + '\'' +
                '}';
    }
}