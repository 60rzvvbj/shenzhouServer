package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Consult;

public interface ConsultService {

    // 发起咨询
    String addConsult(Consult consult);

    // 导游回复
    boolean guideReply(String id, String replyContent);

    // 用户打分
    boolean userScore(String id, int score);

    // 结束咨询
    boolean endConsult(String id);

}
