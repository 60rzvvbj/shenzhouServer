package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Consult;

import java.util.List;

public interface ConsultService {

    // 发起咨询
    String addConsult(Consult consult);

    // 导游回复
    boolean guideReply(String id, String replyContent);

    // 用户打分
    boolean userScore(String id, int score);

    // 结束咨询
    boolean endConsult(String id);

    // 查看某个用户发起的咨询
    List<Consult> getUserConsult(String account);

    // 查看某个导游收到的咨询
    List<Consult> getGuideConsult(String account);

}
