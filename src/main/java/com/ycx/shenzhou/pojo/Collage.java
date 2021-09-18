package com.ycx.shenzhou.pojo;

public class Collage {
    private String id; //拼团ID
    private String account; //发起人账号
    private long pnumber; //总人数
    private String departure; //起点
    private String destination; //终点
    private long dtime; //出发时间
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

    public Long getPnumber() {
        return pnumber;
    }

    public void setPnumber(Long pnumber) {
        this.pnumber = pnumber;
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

    public Long getDtime() {
        return dtime;
    }

    public void setDtime(Long dtime) {
        this.dtime = dtime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "College{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", pnumber=" + pnumber +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", dtime=" + dtime +
                ", describe='" + describe + '\'' +
                '}';
    }
}