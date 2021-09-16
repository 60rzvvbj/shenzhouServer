package com.ycx.shenzhou.pojo;

public class Experience {
    private String account; //用户账号
    private long value; //经验值
    private long level; //等级

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "account='" + account + '\'' +
                ", value=" + value +
                ", level=" + level +
                '}';
    }
}