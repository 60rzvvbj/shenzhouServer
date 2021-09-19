package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.User;

import java.util.List;

public interface UserService {

    // 注册
    boolean register(String account, String pwd, String username);

    // 登录
    boolean login(String account, String pwd);

    // 修改密码
    boolean changePwd(String account, String oldPwd, String newPwd);

    // 通过账号获取token
    String getToken(String account);

    // 验证token
    boolean testToken(String account, String token);

    // 修改余额
    boolean modifyBalance(String account, int money);

    // 获取用户信息
    User getUserInfo(String account);

    // 修改用户信息
    boolean modifyUserInfo(User user);
}
