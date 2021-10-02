package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.CollageMapper;
import com.ycx.shenzhou.mapper.ParticipateMapper;
import com.ycx.shenzhou.mapper.UserMapper;
import com.ycx.shenzhou.pojo.Collage;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.CollageService;
import com.ycx.shenzhou.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service("CollageService")
public class CollageServiceImpl implements CollageService {

    private final int COLLAGE_NUMBER = 11;

    @Autowired
    private CollageMapper collageMapper;

    @Autowired
    private ParticipateMapper participateMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String addCollage(Collage collage) { // 发起拼团
        int experience = 10; // 发起拼团加10点经验值
        collage.setdTime(new Date().getTime());
        collageMapper.addCollage(collage); // 在数据库添加新的拼团信息
        String account = collage.getAccount(); // 获取发起人账号
        ExperienceServiceImpl experienceServiceImpl = new ExperienceServiceImpl();
        experienceServiceImpl.addExperience(account, experience);
        return collage.getId(); // 返回拼团ID
    }

    @Override
    public List<String> getParticipant(String id) { // 查看拼团中参与人(不包括自己)
        return participateMapper.getPaticipateById(id);
    }

    @Override
    public boolean completeCollage(String id) { // 完成拼团
        int money = 1; // 成功完成拼团加1金币
        Collage collage = collageMapper.getCollageById(id);
        String account = collage.getAccount();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.modifyBalance(account, money); // 发起人得到1金币
        List<String> participant = participateMapper.getPaticipateById(id);
        for (String s : participant) {
            userServiceImpl.modifyBalance(s, money); // 拼团完成时，除了发起人，其他参与人金币加1
        }
        return collageMapper.removeCollage(id) && participateMapper.removePaticipateById(id); // 通过ID把拼团申请和参与拼团在数据库删了
    }

    @Override
    public boolean joinCollage(String account, String id) { // 加入拼团
        if (participateMapper.addParticipate(account, id) > 0) { // 在数据库中添加拼团记录
            int experience = 2; // 参与拼团加2点经验值
            ExperienceServiceImpl experienceServiceImpl = new ExperienceServiceImpl();
            experienceServiceImpl.addExperience(account, experience);
            return true;
        }
        return false;
    }

    @Override
    public List<Collage> getSomeCollage() { // 查看一些拼团
        List<Collage> collages = collageMapper.getAllCollage();
        List<Collage> res = new LinkedList<>();

        int n = collages.size();
        int len = COLLAGE_NUMBER;

        // 如果总数大于COLLAGE_NUMBER则在其中随机抽取12个，不可以重复
        if(n > len) {
            int[] randomNum = RandomUtil.getRandomNum(n, len);
            for(int i = 0; i < len; i++){
                res.add(collages.get(randomNum[i]));
            }
        }

        // 如果总数小于COLLAGE_NUMBER则全部返回
        else {
            for (Collage collage : collages) {
                res.add(collage);
            }
        }
        return res;
    }

    @Override
    public List<Collage> getLaunchCollage(String account) { // 查找我发起的拼团
        return collageMapper.getCollageByAccount(account);
    }

    @Override
    public List<Collage> getJoinCollage(String account) { // 查看我参与的拼团
        List<String> collageId = participateMapper.getPaticipateByAccount(account);
        List<Collage> res = new LinkedList<>();
        for (String s : collageId) {
            res.add(collageMapper.getCollageById(s));
        }
        return res;
    }

    @Override
    public Collage getCollage(String id) { // 通过ID查找拼团
        return collageMapper.getCollageById(id);
    }
}