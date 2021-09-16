package com.ycx.shenzhou.pojo;

public class Exchangegift {
    private String id; //兑换ID
    private String account; //用户账号
    private String gid; //礼品编号
    private long etime; //申请时间
    private String address; //邮寄地址
    private int state; //申请状态 0: 未成功, 1: 邮寄中, 2: 已收货

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

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Long getEtime() {
        return etime;
    }

    public void setEtime(Long etime) {
        this.etime = etime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Exchangegift{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", gid='" + gid + '\'' +
                ", etime=" + etime +
                ", address='" + address + '\'' +
                ", state=" + state +
                '}';
    }
}