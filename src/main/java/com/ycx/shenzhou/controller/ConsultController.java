package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.Consult;
import com.ycx.shenzhou.service.ConsultService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    private static class InitiateConsultationData {
        public String id;
        public long consultTime;
    }

    @PostMapping("/initiateConsultation")
    public String initiateConsultation(HttpServletRequest request, Consult consult) {
        String account = (String) request.getAttribute("account");
        consult.setAccount(account);
        String id = consultService.addConsult(consult);

        BaseResult baseResult;
        if (id != null) {
            InitiateConsultationData data = new InitiateConsultationData();

            data.id = id;
            data.consultTime = consult.getConsultTime();

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("咨询成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("咨询失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/guide/replyConsult")
    public String replyConsult(String id, String reply) {
        boolean res = consultService.guideReply(id, reply);

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

    @PostMapping("/scoreConsult")
    public String scoreConsult(String id, int score) {
        boolean res = consultService.userScore(id, score);

        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("评分成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("评分失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class getConsultData {
        public static class ConsultData {
            public String id;
            public String account;
            public long consultTime;
            public String content;
            public String reply;
            public int score;
            public int stage;
        }
    }

    @GetMapping("/getOwnConsult")
    public String getOwnConsult(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");

        return "";
    }

}
