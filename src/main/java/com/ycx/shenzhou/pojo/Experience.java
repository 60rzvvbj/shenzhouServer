package com.ycx.shenzhou.pojo;

public class Experience {
    private String account; // 用户账号
    private int value; // 经验值
    private int level; // 等级

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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