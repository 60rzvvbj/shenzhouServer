package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ExchangeGiftMapper;
import com.ycx.shenzhou.mapper.GiftMapper;
import com.ycx.shenzhou.mapper.UserMapper;
import com.ycx.shenzhou.pojo.ExchangeGift;
import com.ycx.shenzhou.pojo.Gift;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.ExchangeGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("ExchangeGiftService")
public class ExchangeGiftServiceImpl implements ExchangeGiftService {

    @Autowired
    private ExchangeGiftMapper exchangeGiftMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GiftMapper giftMapper;

    @Override
    public String addExchange(ExchangeGift exchangeGift) { // 添加兑换申请
        String account = exchangeGift.getAccount(); // 获取用户账号
        User user = userMapper.getUserByAccount(account);
        String gid = exchangeGift.getGid(); // 获取礼物编号
        Gift gift = giftMapper.getGiftById(gid); // 获取礼物对象
        ExchangeGift exchangeGift1 = exchangeGiftMapper.getExchangeGiftByAccountGid(account, gid); // 获取数据库中是否有此用户申请过该礼品
        if (exchangeGift1 != null && exchangeGift1.getStatus() != -1) { // 该用户申请过该礼品，并且没有被拒
            return null;
        }
        int price = gift.getPrice(); // 礼物的价格
        if (user.getBalance() < price || gift.getStatus() == 1) { // 余额不足或者礼品已兑换，不能换
            return null;
        }
        user.setBalance(user.getBalance() - price); // 可以换，先扣钱
        userMapper.modifyBalance(user); // 操作数据库用户表
        exchangeGift.seteTime(new Date().getTime());
        exchangeGift.setStatus(0); //申请状态为审核中
        exchangeGiftMapper.addExchangeGift(exchangeGift); // 在数据库中添加兑换申请
        return exchangeGift.getId(); // 返回兑换申请ID
    }

    @Override
    public boolean acceptExchange(String id) { // 接受申请
        ExchangeGift exchangeGift = exchangeGiftMapper.getExchangeGiftById(id); // 从数据库中获取兑换申请
        exchangeGift.setStatus(1); // 把status置为邮寄中
        String gid = exchangeGift.getGid(); // 获取礼品编号
        Gift gift = giftMapper.getGiftById(gid); // 获取礼品对象
        gift.setStatus(1); // 礼品已兑换
        return exchangeGiftMapper.modifyExchangeGift(exchangeGift) > 0 && giftMapper.modifyGiftStatus(gift) > 0; // 修改数据库中对应的兑换申请和礼品状态
    }

    @Override
    public boolean rejectExchange(String id) {
        ExchangeGift exchangeGift = exchangeGiftMapper.getExchangeGiftById(id); // 从数据库中获取兑换申请
        if(exchangeGift == null) {
            return false;
        }
        // exchangeGift.setStatus(-1);
        // exchangeGiftMapper.modifyExchangeGift(exchangeGift);
        String account = exchangeGift.getAccount(); // 获取用户账号
        String gid = exchangeGift.getGid(); // 获取礼物编号
        Gift gift = giftMapper.getGiftById(gid); // 获取礼物对象
        int price = gift.getPrice(); // 礼物的价格
        User user = userMapper.getUserByAccount(account); // 获取对应的用户
        user.setBalance(user.getBalance() + price);
        userMapper.modifyBalance(user); // 给用户返还钱
        return exchangeGiftMapper.removeExchangeGift(id); // 从数据库删除兑换申请
    }

    @Override
    public boolean modifyExchangeStatus(String id, int status) {
        ExchangeGift exchangeGift = exchangeGiftMapper.getExchangeGiftById(id); // 从数据库中获取兑换申请
        if (exchangeGift == null) {
            return false;
        }
        if (status == 1) {
            return this.acceptExchange(id);
        }
        if (status == -1) {
            return this.rejectExchange(id);
        }
        exchangeGift.setStatus(status); // 修改status，因为还有个2（已收货），先保留这两行代码
        return exchangeGiftMapper.modifyExchangeGift(exchangeGift) > 0; // 修改数据库中对应的兑换申请;
    }

    @Override
    public boolean removeExchange(String id) {
        return exchangeGiftMapper.removeExchangeGift(id); // 通过ID从数据库中移除兑换申请
    }

    @Override
    public List<ExchangeGift> getAllExchangeGift() {
        return exchangeGiftMapper.getAllExchangeGift(0);
    }
}