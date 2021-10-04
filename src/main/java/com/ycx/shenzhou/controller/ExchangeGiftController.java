package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.ExchangeGift;
import com.ycx.shenzhou.service.ExchangeGiftService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ExchangeGiftController {

    @Autowired
    private ExchangeGiftService exchangeGiftService;

    private static class AddExchangeGiftData {
        public String id;
        public long eTime;
    }

    @PostMapping("/addExchangeGift")
    public String addExchangeGift(HttpServletRequest request, ExchangeGift exchangeGift) {
        String account = (String) request.getAttribute("account");
        exchangeGift.setAccount(account);
        System.out.println(exchangeGift);
        String id = exchangeGiftService.addExchange(exchangeGift);
        BaseResult baseResult;

        if (id != null) {
            AddExchangeGiftData data = new AddExchangeGiftData();
            data.id = id;
            data.eTime = exchangeGift.geteTime();

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("发起成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("发起失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetAllExchangeGiftData {
        public List<ExchangeGift> exchangeGiftList;
    }

    @GetMapping("/admin/getAllExchangeGift")
    public String getAllExchangeGift() {
        List<ExchangeGift> exchangeGifts = exchangeGiftService.getAllExchangeGift();

        BaseResult baseResult;
        if (exchangeGifts != null) {
            GetAllExchangeGiftData data = new GetAllExchangeGiftData();
            data.exchangeGiftList = exchangeGifts;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/admin/handleExchangeGift")
    public String handleExchangeGift(String id, int status) {
        boolean res = exchangeGiftService.modifyExchangeStatus(id, status);
        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("处理成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("处理失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/overExchange")
    public String overExchange(String id) {
        boolean res = exchangeGiftService.removeExchange(id);

        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("结束成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("结束失败");
        }

        return JSONUtil.objectToString(baseResult);
    }
}
