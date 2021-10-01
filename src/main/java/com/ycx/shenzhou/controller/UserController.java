package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.Experience;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.ExperienceService;
import com.ycx.shenzhou.service.PictureService;
import com.ycx.shenzhou.service.UserService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private PictureService pictureService;

    @PostMapping("/register")
    public String register(String account, String password, String username) {
        boolean res = userService.register(account, password, username);
        BaseResult baseData;
        if (res) {

            experienceService.initUserExperience(account); //初始化用户经验等级

            baseData = BaseResult.getSuccessBaseData();
            baseData.setMessage("注册成功");
        } else {
            baseData = BaseResult.getErrorBaseData();
            baseData.setMessage("注册失败");
        }
        return JSONUtil.objectToString(baseData);
    }

    // 登录返回数据
    private static class LoginData {
        public String token;
    }

    @PostMapping ("/login")
    public String login(String account, String password) {
        boolean res = userService.login(account, password);
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

    @PostMapping("/changePwd")
    public String changePwd(HttpServletRequest request, String oldPassword, String newPassword) {
        String account = (String) request.getAttribute("account");
        boolean res = userService.changePwd(account, oldPassword, newPassword);
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

    private static class GetOwnInfoData {
        public String username;
        public String other;
        public int balance;
        public int experience;
        public int level;
        public String headPortraitUrl;
    }

    @GetMapping("/getOwnInfo")
    public String getOwnInfo(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");
        User user = userService.getUserInfo(account);
        BaseResult baseResult;
        if (user != null) {
            GetOwnInfoData data = new GetOwnInfoData();

            // 用户基本信息
            data.username = user.getUsername();
            data.other = user.getOther();
            data.balance = user.getBalance();

            // 用户经验等级
            Experience experience = experienceService.getExperienceByAccount(account);
            data.experience = experience.getValue();
            data.level = experience.getLevel();

            // 用户头像路径
            String headPortraitUrl = pictureService.getUserHeadPortraitUrl(userService.getToken(account));
            data.headPortraitUrl = headPortraitUrl != null ? headPortraitUrl : pictureService.getDefaultPictureUrl(1);

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }
        return JSONUtil.objectToString(baseResult);
    }

    @GetMapping("/modifyUserInfo")
    String modifyUserInfo(HttpServletRequest request, User user) {
        String account = (String) request.getAttribute("account");
        user.setAccount(account);
        boolean res = userService.modifyUserInfo(user);
        BaseResult baseResult;
        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("修改成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("修改失败");
        }
        return JSONUtil.objectToString(baseResult);
    }

    private static class GetUserInfoData {
        public String username;
        public String other;
        public int level;
        public String headPortraitUrl;
    }

    @GetMapping("/public/getUserInfo")
    public String getUserInfo(String account) {
        User user = userService.getUserInfo(account);
        BaseResult baseResult;

        if (user != null) {
            GetUserInfoData data = new GetUserInfoData();
            data.username = user.getUsername();
            data.other = user.getOther();
            data.level = experienceService.getExperienceByAccount(account).getLevel();
            data.headPortraitUrl = pictureService.getUserHeadPortraitUrl(account);

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

}
