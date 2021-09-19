package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.UserMapper;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.UserService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(String account, String pwd, String username) {
        return !account.equals("1808078515");
    }

    @Override
    public boolean login(String account, String pwd) {
        User user = userMapper.getUserByAccount(account);
        return user != null && user.getPwd().equals(pwd);
    }

    @Override
    public boolean changePwd(String account, String oldPwd, String newPwd) {
        return login(account, oldPwd) && userMapper.changePwd(account, newPwd) > 0;
    }

    @Override
    public String getToken(String account) {
        return "" + account.hashCode();
    }

    @Override
    public boolean testToken(String account, String token) {
        return ("" + account.hashCode()).equals(token);
    }

    @Override
    public boolean modifyBalance(String account, int money) {
        User user = userMapper.getUserByAccount(account);
        if (user == null){
            return false;
        }
        user.setBalance(user.getBalance() + money);
        return userMapper.modifyBalance(user) > 0;
    }

    @Override
    public User getUserInfo(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public boolean modifyUserInfo(User user) {
        return userMapper.modifyUserInfo(user) > 0;
    }
}
