package com.ycx.shenzhou.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycx.shenzhou.pojo.BaseData;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.UserService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private class RegisterData {
        public String account;
        public String username;
    }

    @GetMapping("/register")
    public String register(String account, String pwd, String username) {
        boolean res = userService.register(account, pwd, username);
        BaseData baseData;
        if (res) {
            RegisterData registerData = new RegisterData();
            registerData.account = account;
            registerData.username = username;

            baseData = BaseData.getSuccessBaseData();
            baseData.setMessage("注册成功");
            baseData.setData(registerData);
        } else {
            baseData = BaseData.getErrorBaseData();
            baseData.setMessage("注册失败");
        }
        return JSONUtil.objectToString(baseData);
    }
}
