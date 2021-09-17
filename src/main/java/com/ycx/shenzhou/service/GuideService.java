package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Guide;

import java.util.List;

public interface GuideService {
    boolean isGuide(String account);

    Guide getGuide(String account);

    String addGuide(Guide guide);

    boolean modifyGuide(Guide guide);

    List<Guide> getRandomGuide();
}
