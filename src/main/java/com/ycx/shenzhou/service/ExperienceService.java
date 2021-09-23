package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Experience;

import java.util.List;

public interface ExperienceService {

    // 初始化用户经验等级
    boolean initUserExperience(String account);

    // 用户增加经验
    boolean addExperience(String account, int experience);

    // 通过用户账号获取用户经验等级
    Experience getExperienceByAccount(String account);

    // 获取经验等级对照表
    List<Integer> getExperienceTable();

}
