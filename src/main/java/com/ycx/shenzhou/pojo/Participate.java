package com.ycx.shenzhou.pojo;

public class Participate {
    private String account; // 参与人账号
    private String cid; // 拼团ID

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Participate{" +
                "account='" + account + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}