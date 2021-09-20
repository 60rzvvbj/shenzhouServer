package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ConsultMapper;
import com.ycx.shenzhou.pojo.Consult;
import com.ycx.shenzhou.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private ConsultMapper consultMapper;

    @Override
    public String addConsult(Consult consult) { // 发起咨询
        consult.setStage(0); // 咨询阶段默认为0
        consultMapper.addConsult(consult); // 在数据库添加咨询记录
        return consult.getId(); // 获取咨询ID
    }

    @Override
    public boolean guideReply(String id, String replyContent) { // 导游回复
        Consult consult = consultMapper.getConsultById(id); // 从数据库中根据咨询ID获取咨询记录
        consult.setStage(1);    // 咨询阶段设置为导游已经回复咨询
        consult.setReply(replyContent); // 添加导游回复的具体内容
        return consultMapper.modifyConsultReply(consult) > 0;
    }

    @Override
    public boolean userScore(String id, int score) { // 用户评分
        Consult consult = consultMapper.getConsultById(id); // 从数据库中根据咨询ID获取咨询记录
        consult.setScore(score); // 修改consult里的用户评分
        consult.setStage(2); // 用户已评分
        return consultMapper.modifyConsultScore(consult) > 0;
    }

    @Override
    public boolean endConsult(String id) { // 结束咨询
        Consult consult = consultMapper.getConsultById(id); // 从数据库中根据咨询ID获取咨询记录
        consult.setStage(3); // consult的stage改成咨询结束
        return consultMapper.modifyConsultScore(consult) > 0; // 没有只修改stage的方法，这个方法也可以修改stage
    }
}