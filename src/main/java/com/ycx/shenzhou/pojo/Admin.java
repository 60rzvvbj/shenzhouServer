package com.ycx.shenzhou.pojo;

public class Admin {
    private String id; // 管理员ID
    private String account; // 用户账号

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

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}