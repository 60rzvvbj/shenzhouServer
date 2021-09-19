package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @RequestMapping("/error/tokenError")
    public String tokenError() {
        BaseResult baseResult = BaseResult.getErrorBaseData();
        baseResult.setMessage("token error");
        return JSONUtil.objectToString(baseResult);
    }
}
