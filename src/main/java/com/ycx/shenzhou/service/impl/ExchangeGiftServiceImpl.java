package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ExchangeGiftMapper;
import com.ycx.shenzhou.pojo.ExchangeGift;
import com.ycx.shenzhou.service.ExchangeGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("ExchangeGiftService")
public class ExchangeGiftServiceImpl implements ExchangeGiftService {

    @Autowired
    private ExchangeGiftMapper exchangeGiftMapper;

    @Override
    public String addExchange(ExchangeGift exchangeGift) { // 添加兑换申请
        exchangeGift.setStatus(0); //申请状态为审核中
        exchangeGiftMapper.addExchangeGift(exchangeGift); // 在数据库中添加兑换申请
        return exchangeGift.getId(); // 返回兑换申请ID
    }

    @Override
    public boolean acceptExchange(String id) { // 接受申请
        ExchangeGift exchangeGift = exchangeGiftMapper.getExchangeGiftById(id); // 从数据库中获取兑换申请
        exchangeGift.setStatus(1); // 把status置为邮寄中
        return exchangeGiftMapper.modifyExchangeGift(exchangeGift) > 0; // 修改数据库中对应的兑换申请
    }

    @Override
    public boolean rejectExchange(String id) {
        ExchangeGift exchangeGift = exchangeGiftMapper.getExchangeGiftById(id); // 从数据库中获取兑换申请
        exchangeGift.setStatus(-1); // 把status置为申请失败
        return exchangeGiftMapper.modifyExchangeGift(exchangeGift) > 0; // 修改数据库中对应的兑换申请
    }

    @Override
    public boolean modifyExchangeStatus(String id, int status) {
        ExchangeGift exchangeGift = exchangeGiftMapper.getExchangeGiftById(id); // 从数据库中获取兑换申请
        exchangeGift.setStatus(status); // 修改status
        return exchangeGiftMapper.modifyExchangeGift(exchangeGift) > 0; // 修改数据库中对应的兑换申请;
    }

    @Override
    public boolean removeExchange(String id) {
        return exchangeGiftMapper.removeExchangeGift(id); // 通过ID从数据库中移除兑换申请
    }
}