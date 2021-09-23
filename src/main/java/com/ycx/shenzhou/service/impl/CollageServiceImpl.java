package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.CollageMapper;
import com.ycx.shenzhou.mapper.ParticipateMapper;
import com.ycx.shenzhou.pojo.Collage;
import com.ycx.shenzhou.service.CollageService;
import com.ycx.shenzhou.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service("CollageService")
public class CollageServiceImpl implements CollageService {

    private final int COLLAGE_NUMBER = 12;

    @Autowired
    private CollageMapper collageMapper;

    @Autowired
    private ParticipateMapper participateMapper;

    @Override
    public String addCollage(Collage collage) { // 发起拼团
        collage.setdTime(new Date().getTime());
        collageMapper.addCollage(collage); // 在数据库添加新的拼团信息
        return collage.getId(); // 返回拼团ID
    }

    @Override
    public List<String> getParticipant(String id) { // 查看拼团中参与人(不包括自己)
        return participateMapper.getPaticipateById(id);
    }

    @Override
    public boolean completeCollage(String id) { // 完成拼团
        return collageMapper.removeCollage(id) && participateMapper.removePaticipateById(id); // 通过ID把拼团申请和参与拼团在数据库删了
    }

    @Override
    public boolean joinCollage(String account, String id) { // 加入拼团
        return participateMapper.addParticipate(account, id) > 0; // 在数据库中添加拼团记录
    }

    @Override
    public List<Collage> getSomeCollage() { // 查看一些拼团
        List<Collage> collages = collageMapper.getAllCollage();
        List<Collage> res = new LinkedList<>();

        // 如果总数大于COLLAGE_NUMBER则在其中随机抽取12个，不可以重复
        int n = collages.size();
        int len = COLLAGE_NUMBER;
        int[] randomNum = RandomUtil.getRandomNum(n, len);
        if(n > COLLAGE_NUMBER) {
            for(int i = 0; i < 12; i++){
                res.add(collages.get(randomNum[i]));
            }
        }

        // 如果总数小于COLLAGE_NUMBER则全部返回
        else {
            for(int i = 0; i < COLLAGE_NUMBER; i++) {
                res.add(collages.get(i));
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