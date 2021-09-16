package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.service.UserService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(HttpServletRequest request, String account, String pwd, String username) {
        boolean res = userService.register(account, pwd, username);
        BaseResult baseData;
        if (res) {
            baseData = BaseResult.getSuccessBaseData();
            baseData.setMessage("注册成功");
        } else {
            baseData = BaseResult.getErrorBaseData();
            baseData.setMessage("注册失败");
        }
        return JSONUtil.objectToString(baseData);
    }

    private class LoginData {
        public String token;
    }

    @GetMapping("/login")
    public String login(String account, String pwd) {
        boolean res = userService.login(account, pwd);
        BaseResult baseData;
        if (res) {
            LoginData loginData = new LoginData();
            loginData.token = userService.getToken(account);

            baseData = BaseResult.getSuccessBaseData();
            baseData.setMessage("登录成功");
            baseData.setData(loginData);
        } else {
            baseData = BaseResult.getErrorBaseData();
            baseData.setMessage("用户名或密码错误");
        }
        return JSONUtil.objectToString(baseData);
    }

    @GetMapping("/changePwd")
    public String changePwd(String account, String oldPwd, String newPwd) {
        boolean res = userService.changePwd(account, oldPwd, newPwd);
        BaseResult baseData;
        if (res) {
            baseData = BaseResult.getSuccessBaseData();
            baseData.setMessage("修改成功");
        } else {
            baseData = BaseResult.getErrorBaseData();
            baseData.setMessage("修改失败");
        }
        return JSONUtil.objectToString(baseData);
    }
}
