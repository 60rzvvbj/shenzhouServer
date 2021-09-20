package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ExperienceMapper;
import com.ycx.shenzhou.pojo.Experience;
import com.ycx.shenzhou.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ExperienceService")
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceMapper experienceMapper;

    @Override
    public boolean initUserExperience(String account) { // 初始化用户经验等级
        Experience experience = new Experience(); // 创建一个新对象
        experience.setAccount(account); // 指明experience是属于哪个account
        experience.setLevel(0); // 初始化用户等级
        experience.setValue(0); // 初始化用户经验值
        return experienceMapper.addExperience(experience) > 0; // 添加到数据库中
    }

    @Override
    public boolean addExperience(String account, int experience) { // 用户增加经验，升级规则还未定，这个暂时不写

        return false;
    }

    @Override
    public Experience getExperienceByAccount(String account) { // 通过用户账号获取用户经验等级
        return experienceMapper.getExperienceByAccount(account);
    }
}
