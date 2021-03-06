package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ConsultMapper;
import com.ycx.shenzhou.mapper.GuideMapper;
import com.ycx.shenzhou.pojo.Consult;
import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.service.GuideService;
import com.ycx.shenzhou.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("GuideService")
public class GuideServiceImpl implements GuideService {

    private final int GUIDE_NUMBER = 12;

    @Autowired
    private GuideMapper guideMapper;

    @Autowired
    private ConsultMapper consultMapper;

    @Override
    public boolean isGuide(String account) {
        return guideMapper.getGuideByAccount(account) != null;
    }

    @Override
    public Guide getGuide(String account) {
        Guide guide = guideMapper.getGuideByAccount(account);
        if (guide == null) {
            return null;
        }
        String id = guide.getId();
        List<Consult> consults = consultMapper.getConsultByGid(id);
        int cnt = 0, sum = 0;
        for (Consult consult : consults) {
            if (consult.getStage() == 2 || consult.getStage() == 3) {
                cnt++;
                sum += consult.getScore();
            }
        }
        if (cnt == 0) {
            guide.setScore(-1);
        }
        else {
            guide.setScore(1.0 * sum / cnt);
        }
        return guide;
    }

    @Override
    public Guide getGuideById(String id) {
        Guide guide = guideMapper.getGuideById(id);
        if (guide == null) {
            return null;
        }
        List<Consult> consults = consultMapper.getConsultByGid(id);
        int cnt = 0, sum = 0;
        for (Consult consult : consults) {
            if (consult.getStage() == 2 || consult.getStage() == 3) {
                cnt++;
                sum += consult.getScore();
            }
        }
        if (cnt == 0) {
            guide.setScore(-1);
        }
        else {
            guide.setScore(1.0 * sum / cnt);
        }
        return guide;
    }

    @Override
    public String addGuide(Guide guide) {
        guideMapper.addGuide(guide);
        return guide.getId();
    }

    @Override
    public boolean modifyGuide(Guide guide) {
        return guideMapper.modifyGuide(guide) > 0;
    }

    @Override
    public List<Guide> getRandomGuide() {
        List<Guide> guides = guideMapper.getAllGuide();
        for (Guide value : guides) {
            String id = value.getId();
            List<Consult> consults = consultMapper.getConsultByGid(id);
            int cnt = 0, sum = 0;
            for (Consult consult : consults) {
                if (consult.getStage() == 2 || consult.getStage() == 3) {
                    cnt++;
                    sum += consult.getScore();
                }
            }
            if (cnt == 0) {
                value.setScore(-1);
            } else {
                value.setScore((int)(1.0 * sum / cnt * 10) / 10.0);
            }
        }

        List<Guide> res = new LinkedList<>();
        int n = guides.size();
        int len = GUIDE_NUMBER;

        // ??????????????????GUIDE_NUMBER????????????????????????12?????????????????????
        if(n > len) {
            int[] randomNum = RandomUtil.getRandomNum(n, len);
            for(int i = 0; i < len; i++){
                res.add(guides.get(randomNum[i]));
            }
        }

        // ??????????????????GUIDE_NUMBER???????????????
        else {
            for (Guide guide : guides) {
                res.add(guide);
            }
        }
        return res;
    }
}
