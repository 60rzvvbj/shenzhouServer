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
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public boolean register(String account, String pwd, String username) {
        return !account.equals("1808078515");
    }

    @Override
    public boolean login(String account, String pwd) {
        User user = userMapper.getUserByAccount(account);
        System.out.println(JSONUtil.objectToString(user));
        return false;
    }

    @Override
    public boolean changePwd(String account, String oldPwd, String newPwd) {
        return false;
    }
}
