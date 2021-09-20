package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.GuideApply;

public interface GuideApplyService {

    // 添加导游申请
    String addGuideApply(GuideApply guideApply);

    // 接受导游申请
    boolean acceptGuideApply(String id);

    // 拒绝导游申请
    boolean rejectGuideApply(String id);

    // 取消导游申请
    boolean cancelGuideApply(String id);

}
