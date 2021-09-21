package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.CollageMapper;
import com.ycx.shenzhou.mapper.ParticipateMapper;
import com.ycx.shenzhou.pojo.Collage;
import com.ycx.shenzhou.service.CollageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CollageService")
public class CollageServiceImpl implements CollageService {

    @Autowired
    private CollageMapper collageMapper;
    private ParticipateMapper participateMapper;

    @Override
    public String addCollage(Collage collage) { // 发起拼团
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
}