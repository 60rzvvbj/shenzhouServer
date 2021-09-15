package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.UserMapper;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.UserService;
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
}
