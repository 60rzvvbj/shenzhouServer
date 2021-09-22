package com.ycx.shenzhou.controller;


import com.ycx.shenzhou.pojo.Collage;
import com.ycx.shenzhou.service.CollageService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class CollageController {

    @Autowired
    private CollageService collageService;

    private static class AddCollageData {
        public String id;
    }

    @PostMapping("/addCollage")
    public String addCollage(HttpServletRequest request, Collage collage) {
        String account = (String) request.getAttribute("account");
        collage.setAccount(account);
        String id = collageService.addCollage(collage);

        BaseResult baseResult;

        if (id != null) {
            AddCollageData data = new AddCollageData();
            data.id = id;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("发起成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("发起失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetParticipantData {
        public List<String> userList;
    }

    @GetMapping("/getParticipant")
    public String getParticipant(HttpServletRequest request, String id) {
        String account = (String) request.getAttribute("account");

        GetParticipantData data = new GetParticipantData();
        data.userList = collageService.getParticipant(id);

        BaseResult baseResult;

        if (data.userList != null) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/completeCollage")
    public String completeCollage(HttpServletRequest request, String id) {
        String account = (String) request.getAttribute("account");
        Collage collage = collageService.getCollage(id);

        boolean res;
        if (collage.getAccount().equals(account)) {
            res = collageService.completeCollage(id);
        } else {
            res = false;
        }

        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("完成成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("完成失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetCollageData {
        public List<Collage> collageList;
    }

    @GetMapping("/getLaunchCollage")
    public String getLaunchCollage(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");
        List<Collage> collages = collageService.getLaunchCollage(account);

        BaseResult baseResult;

        if (collages != null) {
            GetCollageData data = new GetCollageData();
            data.collageList = collages;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @GetMapping("/getJoinCollage")
    public String getJoinCollage(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");
        List<Collage> collages = collageService.getJoinCollage(account);

        BaseResult baseResult;

        if (collages != null) {
            GetCollageData data = new GetCollageData();
            data.collageList = collages;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @GetMapping("/public/getSomeCollage")
    public String getSomeCollage() {
        List<Collage> collages = collageService.getSomeCollage();

        BaseResult baseResult;

        if (collages != null) {
            GetCollageData data = new GetCollageData();
            data.collageList = collages;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/joinCollage")
    public String joinCollage(HttpServletRequest request, String id) {
        String account = (String) request.getAttribute("account");
        boolean res = collageService.joinCollage(account, id);

        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("加入成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("加入失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

}
