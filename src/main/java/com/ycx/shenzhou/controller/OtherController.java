package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.service.ArticleService;
import com.ycx.shenzhou.service.ExchangeGiftService;
import com.ycx.shenzhou.service.ExperienceService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OtherController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private ArticleService articleService;

    private static class GetExperienceTableData {
        public List<Integer> experienceTable;
    }

    @GetMapping("/getExperienceTable")
    public String getExperienceTable() {
        GetExperienceTableData data = new GetExperienceTableData();
        data.experienceTable = experienceService.getExperienceTable();

        BaseResult baseResult = BaseResult.getSuccessBaseData();
        baseResult.setMessage("获取成功");
        baseResult.setData(data);

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetRewardPriceTableData{
        public List<Integer> priceList;
    }

    @GetMapping("/getRewardPriceTable")
    public String getRewardPriceTable() {
        GetRewardPriceTableData data = new GetRewardPriceTableData();
        data.priceList = articleService.getRewardPriceTable();

        BaseResult baseResult = BaseResult.getSuccessBaseData();
        baseResult.setMessage("获取成功");
        baseResult.setData(data);

        return JSONUtil.objectToString(baseResult);
    }

}
