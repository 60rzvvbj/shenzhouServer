package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Guide;

import java.util.List;

public interface GuideService {

    // 判断用户是不是导游
    boolean isGuide(String account);

    // 通过账号获取导游信息
    Guide getGuide(String account);

    // 添加导游
    String addGuide(Guide guide);

    // 修改导游信息
    boolean modifyGuide(Guide guide);

    // 随机获取n个导游信息
    List<Guide> getRandomGuide();
}
