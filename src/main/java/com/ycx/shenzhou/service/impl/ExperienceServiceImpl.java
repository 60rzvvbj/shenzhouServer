package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ExperienceMapper;
import com.ycx.shenzhou.pojo.Experience;
import com.ycx.shenzhou.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("ExperienceService")
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceMapper experienceMapper;



    // 经验对照表
    private final int[] EXPERIENCE_LEVER = {10, 30, 100, 400, 1000, 3000, 8000, 15000, 50000, 100000};
    private final int[] GOLD_AWARD = {2, 5, 15, 50, 100, 200, 500, 1000, 3000, 10000};

    @Override
    public boolean initUserExperience(String account) { // 初始化用户经验等级
        Experience experience = new Experience(); // 创建一个新对象
        experience.setAccount(account); // 指明experience是属于哪个account
        experience.setLevel(1); // 初始化用户等级
        experience.setValue(0); // 初始化用户经验值
        return experienceMapper.addExperience(experience) > 0; // 添加到数据库中
    }

    @Override
    public boolean addExperience(String account, int experience) { // 用户增加经验
        System.out.println(account);
        Experience experience1 = experienceMapper.getExperienceByAccount(account); //  获取到对应的对象
        int level = experience1.getLevel(); // 初始等级
        int value = experience1.getValue(); // 初始经验
        int money = 0; // 获得金币初始化为0
        value += experience;
        if (level <= EXPERIENCE_LEVER.length && value >= EXPERIENCE_LEVER[level - 1]) {
            value -= EXPERIENCE_LEVER[level - 1];
            level++;
            money += GOLD_AWARD[level - 1];
        }
        experience1.setValue(value);
        experience1.setLevel(level);
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        return experienceMapper.modifyExperience(experience1) > 0 && userServiceImpl.modifyBalance(account, money);
    }

    @Override
    public Experience getExperienceByAccount(String account) { // 通过用户账号获取用户经验等级
        return experienceMapper.getExperienceByAccount(account);
    }

    @Override
    public List<Integer> getExperienceTable() {
        List<Integer> res = new LinkedList<>();
        for (int experience : EXPERIENCE_LEVER) {
            res.add(experience);
        }
        return res;
    }
}
