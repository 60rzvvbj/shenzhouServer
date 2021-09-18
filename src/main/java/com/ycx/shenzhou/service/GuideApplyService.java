package com.ycx.shenzhou.service;

public interface GuideApplyService {

    // 添加导游申请
    String addGuideApply(GuideService guideService);

    // 接受导游申请
    String acceptGuideApply(String id);

    // 拒绝导游申请
    String rejectGuideApply(String id);

    // 取消导游申请
    String cancelGuideApply(String id);

}
