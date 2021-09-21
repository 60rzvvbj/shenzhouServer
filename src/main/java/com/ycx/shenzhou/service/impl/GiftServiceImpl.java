package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.GiftMapper;
import com.ycx.shenzhou.pojo.Gift;
import com.ycx.shenzhou.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;

public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftMapper giftMapper;

    @Override
    public String addGift(Gift gift) { // 添加礼品
        gift.setStatus(0); // 初始化礼品状态(未兑换)
        giftMapper.addGift(gift); // 在数据库中添加礼品信息
        return gift.getId(); // 获取礼品的ID
    }

    @Override
    public boolean modifyGift(Gift gift) { // 修改礼品信息
        return giftMapper.modifyGift(gift) > 0; // 在数据库中修改礼品信息
    }

    @Override
    public boolean exchangeGift(String id) { // 兑换礼品
        Gift gift = giftMapper.getGiftById(id); // 通过礼品ID从数据库中获取相应的礼品
        gift.setStatus(2); // 把礼品状态置成已兑换
        return giftMapper.modifyGiftStatus(gift) > 0; // 从数据库中修改相应的礼品状态
    }

    @Override
    public boolean removeGift(String id) { // 移除礼品
        return giftMapper.removeGift(id); // 从数据库中移除礼品
    }
}