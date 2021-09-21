package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @RequestMapping("/error/accountError")
    public String accountError() {
        BaseResult baseResult = BaseResult.getErrorBaseData();
        baseResult.setMessage("登录状态异常");
        return JSONUtil.objectToString(baseResult);
    }

    @RequestMapping("/error/tokenError")
    public String tokenError() {
        BaseResult baseResult = BaseResult.getErrorBaseData();
        baseResult.setMessage("令牌异常");
        return JSONUtil.objectToString(baseResult);
    }

    @RequestMapping("/error/identityError")
    public String identityError() {
        BaseResult baseResult = BaseResult.getErrorBaseData();
        baseResult.setMessage("身份异常");
        return JSONUtil.objectToString(baseResult);
    }
}
