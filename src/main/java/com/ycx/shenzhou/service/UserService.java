package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    boolean register(String account, String pwd, String username);

    boolean login(String account, String pwd);

    boolean changePwd(String account, String oldPwd, String newPwd);

    String getToken(String account);

    boolean testToken(String account, String token);
}
