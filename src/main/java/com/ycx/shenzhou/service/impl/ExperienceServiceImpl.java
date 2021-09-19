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
    public boolean initUserExperience(String account) {
        return false;
    }

    @Override
    public boolean addExperience(String account, int experience) {
        return false;
    }

    @Override
    public Experience getExperienceByAccount(String account) {
        return experienceMapper.getExperienceByAccount(account);
    }
}
