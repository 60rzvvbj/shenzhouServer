package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.service.GuideService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class GuideController {

    @Autowired
    private GuideService guideService;

    private static class GetSomeGuideData {
        public static class GuideData {
            public String id;
            public String guideAccount;
            public String introduction;
            public double score;
        }

        public List<GuideData> guideList;
    }

    @GetMapping("/public/getSomeGuide")
    public String getSomeGuide() {
        List<Guide> guides = guideService.getRandomGuide();
        BaseResult baseResult;

        if (guides != null) {
            GetSomeGuideData data = new GetSomeGuideData();
            data.guideList = new LinkedList<>();

            for (Guide guide : guides) {
                GetSomeGuideData.GuideData guideData = new GetSomeGuideData.GuideData();

                guideData.id = guide.getId();
                guideData.guideAccount = guide.getAccount();
                guideData.introduction = guide.getIntroduction();
                guideData.score = guide.getScore();

                data.guideList.add(guideData);
            }

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetOwnGuideInfoData {
        public String id;
        public String introduction;
        public double score;
    }

    @GetMapping("/getOwnGuideInfo")
    public String getOwnGuideInfo(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");

        Guide guide = guideService.getGuide(account);

        BaseResult baseResult;

        if (guide != null) {
            GetOwnGuideInfoData data = new GetOwnGuideInfoData();

            data.id = guide.getId();
            data.introduction = guide.getIntroduction();
            data.score = guide.getScore();

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }
        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/modifyGuideIntroduction")
    public String modifyGuideIntroduction(HttpServletRequest request, String introduction) {
        String account = (String) request.getAttribute("account");
        boolean res;

        Guide guide = guideService.getGuide(account);
        res = guide != null;
        if (res) {
            guide.setIntroduction(introduction);
        }
        res = guideService.modifyGuide(guide);

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
}
