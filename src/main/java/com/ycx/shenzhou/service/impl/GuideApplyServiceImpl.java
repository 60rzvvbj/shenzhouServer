package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.GuideApplyMapper;
import com.ycx.shenzhou.mapper.GuideMapper;
import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.pojo.GuideApply;
import com.ycx.shenzhou.service.ExperienceService;
import com.ycx.shenzhou.service.GuideApplyService;
import com.ycx.shenzhou.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("GuideApplyService")
public class GuideApplyServiceImpl implements GuideApplyService {

    @Autowired
    private GuideApplyMapper guideApplyMapper;

    @Autowired
    private GuideMapper guideMapper;

    @Autowired
    private ExperienceService experienceService;

    @Override
    public String addGuideApply(GuideApply guideApply) { // 添加导游申请
        guideApply.setApplyTime(new Date().getTime());
        guideApply.setStatus(0);
        guideApplyMapper.addGuideApply(guideApply); // 在数据库中添加导游申请
        return guideApply.getId(); // 返回导游申请的ID
    }

    @Override
    public List<GuideApply> getAllGuideApply() { // 查看所有导游申请（只查审核中）
        return guideApplyMapper.getAllGuideApply(0);
    }

    @Override
    public GuideApply getGuideApply(String account) { // 查看用户的导游申请
        return guideApplyMapper.getGuideApplyByAccount(account);
    }

    @Override
    public boolean acceptGuideApply(String id) { // 接受导游申请
        int experience = 100; // 成为导游加100点经验值
        GuideApply guideApply = guideApplyMapper.getGuideApplyById(id); // 从数据库中获取导游申请
        guideApply.setStatus(1);
        guideApplyMapper.modifyGiftApplyStatus(guideApply);
        Guide guide = new Guide(); // 创建一个新的导游对象
        guide.setAccount(guideApply.getAccount()); // 复制用户账号
        guide.setIntroduction(guideApply.getIntroduction()); // 复制导游简介
        if (guideMapper.addGuide(guide) > 0) { // 在数据库中添加新的导游
            String account = guide.getAccount();
            experienceService.addExperience(account, experience);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectGuideApply(String id) { // 拒绝导游申请
        GuideApply guideApply = guideApplyMapper.getGuideApplyById(id); // 从数据库中获取导游申请
        guideApply.setStatus(2);  // 修改状态为2
        return guideApplyMapper.modifyGiftApplyStatus(guideApply) > 0; // 从数据库中修改申请状态
    }

    @Override
    public boolean cancelGuideApply(String id) { // 取消导游申请
        return guideApplyMapper.removeGuideApply(id); // 从数据库中删除导游申请
    }
}