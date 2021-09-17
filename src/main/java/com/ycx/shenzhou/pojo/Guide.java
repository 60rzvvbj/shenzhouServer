package com.ycx.shenzhou.pojo;

public class Guide {
    private String id; //导游编号
    private String account; //用户账号
    private String introduction; //导游简介
    private double score;//评分

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", introduction='" + introduction + '\'' +
                ", score=" + score +
                '}';
    }
}