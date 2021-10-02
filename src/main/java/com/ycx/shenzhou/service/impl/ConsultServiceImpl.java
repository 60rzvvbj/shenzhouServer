package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ConsultMapper;
import com.ycx.shenzhou.mapper.GuideMapper;
import com.ycx.shenzhou.pojo.Consult;
import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.service.ConsultService;
import com.ycx.shenzhou.service.ExperienceService;
import com.ycx.shenzhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ConsultService")
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private ConsultMapper consultMapper;

    @Autowired
    private GuideMapper guideMapper;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private UserService userService;

    @Override
    public String addConsult(Consult consult) { // 发起咨询
        int experience = 2; // 发起咨询加2点经验值
        consult.setConsultTime(new Date().getTime());
        consult.setStage(0); // 咨询阶段默认为0
        consultMapper.addConsult(consult); // 在数据库添加咨询记录
        String account = consult.getAccount();
        experienceService.addExperience(account, experience);
        return consult.getId(); // 获取咨询ID
    }

    @Override
    public boolean guideReply(String id, String replyContent) { // 导游回复
        int experience = 15; // 导游回答用户咨询加15点经验值
        Consult consult = consultMapper.getConsultById(id); // 从数据库中根据咨询ID获取咨询记录
        consult.setStage(1);    // 咨询阶段设置为导游已经回复咨询
        consult.setReply(replyContent); // 添加导游回复的具体内容
        if (consultMapper.modifyConsultReply(consult) > 0) {
            String gid = consult.getGid(); // 导游编号
            Guide guide = guideMapper.getGuideById(gid); // 获取导游对象
            String account = guide.getAccount(); // 获取导游的用户编号
            experienceService.addExperience(account, experience);
            return true;
        }
        return false;
    }

    @Override
    public boolean userScore(String id, int score) { // 用户评分
        Consult consult = consultMapper.getConsultById(id); // 从数据库中根据咨询ID获取咨询记录
        consult.setScore(score); // 修改consult里的用户评分
        consult.setStage(2); // 用户已评分
        if (consultMapper.modifyConsultScore(consult) > 0) {
            if (score > 8) {
                int money = 2; // 导游答复评分高于8分加2金币
                String gid = consult.getGid();
                Guide guide = guideMapper.getGuideById(gid);
                String account = guide.getAccount();
                userService.modifyBalance(account, money);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean endConsult(String id) { // 结束咨询
        Consult consult = consultMapper.getConsultById(id); // 从数据库中根据咨询ID获取咨询记录
        consult.setStage(3); // consult的stage改成咨询结束
        return consultMapper.modifyConsultScore(consult) > 0; // 没有只修改stage的方法，这个方法也可以修改stage
    }

    @Override
    public List<Consult> getUserConsult(String account) {
        return consultMapper.getConsultByAccount(account);
    }

    @Override
    public List<Consult> getGuideConsult(String account) {
        return consultMapper.getConsultByGid(account);
    }

    @Override
    public double getGuideScore(String gid) {
        List<Integer> score = consultMapper.getScoreById(gid);
        int scores = 0;
        for (Integer integer : score) {
            scores += integer;
        }
        return 1.0 * scores / score.size();
    }
}