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

    // 加入拼团
    boolean joinCollage(String account, String id);

    // 查看一些拼团
    List<Collage> getSomeCollage();

    // 查找我发起的拼团
    List<Collage> getLaunchCollage(String account);

    // 查看我参与的拼团
    List<Collage> getJoinCollage(String account);

    // 通过ID查找拼团
    Collage getCollage(String id);
}
