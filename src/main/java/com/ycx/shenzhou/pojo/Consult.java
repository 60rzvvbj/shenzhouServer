package com.ycx.shenzhou.pojo;

public class Consult {
    private String id; //咨询ID
    private String account; //用户账号
    private String gid; //导游编号
    private Long consulttime; //咨询时间
    private String content; //咨询内容
    private String reply; //导游答复内容
    private int score; //用户评分
    private int stage; //咨询阶段 0: 用户发起咨询, 1: 导游回复咨询

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

    public long getConsulttime() {
        return consulttime;
    }

    public void setConsulttime(long consulttime) {
        this.consulttime = consulttime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "Consult{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", gid='" + gid + '\'' +
                ", consulttime=" + consulttime +
                ", content='" + content + '\'' +
                ", reply='" + reply + '\'' +
                ", score=" + score +
                ", stage=" + stage +
                '}';
    }
}