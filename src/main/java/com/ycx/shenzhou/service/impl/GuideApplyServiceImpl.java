package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.GuideApplyMapper;
import com.ycx.shenzhou.mapper.GuideMapper;
import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.pojo.GuideApply;
import com.ycx.shenzhou.service.GuideApplyService;
import com.ycx.shenzhou.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;

public class GuideApplyServiceImpl implements GuideApplyService {

    @Autowired
    private GuideApplyMapper guideApplyMapper;
    private GuideMapper guideMapper;

    @Override
    public String addGuideApply(GuideApply guideApply) { // 添加导游申请
        guideApplyMapper.addGuideApply(guideApply); // 在数据库中添加导游申请
        return guideApply.getId(); // 返回导游申请的ID
    }

    @Override
    public boolean acceptGuideApply(String id) { // 接受导游申请
        GuideApply guideApply = guideApplyMapper.getGuideApplyById(id); // 从数据库中获取导游申请
        Guide guide = new Guide(); // 创建一个新的导游对象
        guide.setAccount(guideApply.getAccount()); // 复制用户账号
        guide.setIntroduction(guideApply.getIntroduction()); // 复制导游简介
        return guideMapper.addGuide(guide) > 0; // 在数据库中添加新的导游
    }

    @Override
    public boolean rejectGuideApply(String id) { // 拒绝导游申请
        return cancelGuideApply(id); // 调用取消导游申请
    }

    @Override
    public boolean cancelGuideApply(String id) { // 取消导游申请
        return guideApplyMapper.removeGuideApply(id); // 从数据库中删除导游申请
    }
}