package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Gift;

public interface GiftService {

    // 添加礼品
    String addGift(Gift gift);

    // 修改礼品信息
    boolean modifyGift(Gift gift);

    // 兑换礼品
    boolean exchangeGift(String id);

    // 移除礼品
    boolean removeGift(String id);

}
