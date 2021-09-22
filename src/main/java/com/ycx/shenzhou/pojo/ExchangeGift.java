package com.ycx.shenzhou.pojo;

public class ExchangeGift {
    private String id; // 兑换ID
    private String account; // 用户账号
    private String gid; // 礼品编号
    private long eTime; // 申请时间
    private String address; // 邮寄地址
    private int status; // 申请状态 -1: 申请失败, 0: 审核中, 1: 邮寄中, 2: 已收货

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

    public long geteTime() {
        return eTime;
    }

    public void seteTime(long eTime) {
        this.eTime = eTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExchangeGift{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", gid='" + gid + '\'' +
                ", eTime=" + eTime +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}