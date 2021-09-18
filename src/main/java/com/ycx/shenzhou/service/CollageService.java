package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Collage;

import java.util.List;

public interface CollageService {

    // 发起拼团
    String addCollage(Collage collage);

    // 查看拼团中参与人(不包括自己)
    List<String> getParticipant(String id);

    // 完成拼团
    boolean completeCollage(String id);

}
