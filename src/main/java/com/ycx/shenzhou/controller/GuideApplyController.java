package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.GuideApply;
import com.ycx.shenzhou.service.GuideApplyService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class GuideApplyController {

    @Autowired
    private GuideApplyService guideApplyService;

    private static class GuideApplyData {
        public String id;
        public long applyTime;
    }

    @PostMapping("/guideApply")
    public String guideApply(HttpServletRequest request, String introduction) {
        String account = (String) request.getAttribute("account");

        GuideApply guideApply = new GuideApply();
        guideApply.setAccount(account);
        guideApply.setIntroduction(introduction);

        String id = guideApplyService.addGuideApply(guideApply);

        BaseResult baseResult;

        if (id != null) {
            GuideApplyData data = new GuideApplyData();
            data.id = id;
            data.applyTime = guideApply.getApplyTime();

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("申请成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("申请失败");
        }
        return JSONUtil.objectToString(baseResult);
    }

    @GetMapping("/cancelGuideApply")
    public String cancelGuideApply(HttpServletRequest request, String id) {
        String account = (String) request.getAttribute("account");
        GuideApply guideApply = guideApplyService.getGuideApply(account);

        boolean res;
        BaseResult baseResult;

        if (guideApply.getId().equals(id)) {
            res = guideApplyService.cancelGuideApply(id);
        } else {
            res = false;
        }

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("取消成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("取消失败");
        }
        return JSONUtil.objectToString(baseResult);
    }

    private static class GetOwnGuideApplyData {
        public String id;
        public long applyTime;
        public String introduction;
        public int status;
    }

    @GetMapping("/getOwnGuideApply")
    public String getOwnGuideApply(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");

        GuideApply guideApply = guideApplyService.getGuideApply(account);

        BaseResult baseResult;

        if (guideApply != null) {
            GetOwnGuideApplyData data = new GetOwnGuideApplyData();
            data.id = guideApply.getId();
            data.applyTime = guideApply.getApplyTime();
            data.introduction = guideApply.getIntroduction();
            data.status = guideApply.getStatus();

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetGuideAppliesData {
        public static class GuideApplyData {
            public String id;
            public long applyTime;
            public String account;
            public String introduction;
        }

        public List<GuideApplyData> guideApplyList;
    }

    @GetMapping("/admin/getGuideApplies")
    public String getGuideApplies() {
        List<GuideApply> guideApplies = guideApplyService.getAllGuideApply();

        BaseResult baseResult;

        if (guideApplies != null) {
            GetGuideAppliesData data = new GetGuideAppliesData();
            data.guideApplyList = new LinkedList<>();

            for (GuideApply guideApply : guideApplies) {
                GetGuideAppliesData.GuideApplyData guideApplyData = new GetGuideAppliesData.GuideApplyData();

                guideApplyData.id = guideApply.getId();
                guideApplyData.account = guideApply.getAccount();
                guideApplyData.applyTime = guideApply.getApplyTime();
                guideApplyData.introduction = guideApply.getIntroduction();

                data.guideApplyList.add(guideApplyData);
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

    @GetMapping("/admin/handleGuideApply")
    public String handleGuideApply(String id, boolean handle) {
        boolean res;
        if (handle) {
            res = guideApplyService.acceptGuideApply(id);
        } else {
            res = guideApplyService.rejectGuideApply(id);
        }
        String m = handle ? "接受" : "拒绝";
        BaseResult baseResult;
        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage(m + "成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage(m + "失败");
        }
        return JSONUtil.objectToString(baseResult);
    }
}
