package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Exchangegift;

public interface ExchangeGiftService {

    // 添加兑换申请
    String addExchange(Exchangegift exchangeGift);

    // 接受申请
    boolean acceptExchange(String id);

    // 拒绝申请
    boolean rejectExchange(String id);

    // 修改状态
    boolean modifyExchangeStatus(String id, int status);

    // 删除申请
    boolean removeExchange(String id);
}
