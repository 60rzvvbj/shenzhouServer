package com.ycx.shenzhou.pojo;

public class User {
    private String account; // 账号
    private String pwd; // 密码
    private String username; // 用户名
    private String other; // 其它信息
    private int balance; // 余额

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", username='" + username + '\'' +
                ", other='" + other + '\'' +
                ", balance=" + balance +
                '}';
    }
}
