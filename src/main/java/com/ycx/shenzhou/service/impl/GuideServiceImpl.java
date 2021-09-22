package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.GuideMapper;
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

    @Override
    public boolean isGuide(String account) {
        return guideMapper.getGuideByAccount(account) != null;
    }

    @Override
    public Guide getGuide(String account) {
        return guideMapper.getGuideByAccount(account);
    }

    @Override
    public Guide getGuideById(String id) {
        return guideMapper.getGuideById(id);
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
    // 未测试
    public List<Guide> getRandomGuide() {
        List<Guide> guides = guideMapper.getAllGuide();
        List<Guide> res = new LinkedList<>();

        // 如果总数大于GUIDE_NUMBER则在其中随机抽取12个，不可以重复
        int n = guides.size();
        int len = 12;
        int[] randomNum = RandomUtil.getRandomNum(n, len);
        if(n > GUIDE_NUMBER) {
            for(int i = 0; i < 12; i++){
                res.add(guides.get(randomNum[i]));
            }
        }

        // 如果总数小于GUIDE_NUMBER则全部返回
        else {
           for(int i = 0; i < GUIDE_NUMBER; i++){
               res.add(guides.get(i));
           }
        }
        return res;
    }
}
