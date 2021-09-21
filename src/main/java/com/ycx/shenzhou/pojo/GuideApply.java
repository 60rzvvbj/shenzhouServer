package com.ycx.shenzhou.pojo;

public class GuideApply {
    private String id; // 申请ID
    private long applytime; // 申请时间
    private String account; // 用户账号
    private String introduction; // 导游简介
    private int status; // 申请状态 0:审核中, 1:申请成功, 2:申请失败

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getApplytime() {
        return applytime;
    }

    public void setApplytime(long applytime) {
        this.applytime = applytime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GuideApply{" +
                "id='" + id + '\'' +
                ", applytime=" + applytime +
                ", account='" + account + '\'' +
                ", introduction='" + introduction + '\'' +
                ", status=" + status +
                '}';
    }
}