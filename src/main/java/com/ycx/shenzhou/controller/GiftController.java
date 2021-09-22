package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.Gift;
import com.ycx.shenzhou.pojo.Picture;
import com.ycx.shenzhou.service.GiftService;
import com.ycx.shenzhou.service.PictureService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GiftController {

    @Autowired
    private GiftService giftService;

    @Autowired
    private PictureService pictureService;

    private static class AddGiftData {
        public String id;
    }

    @PostMapping("/admin/addGift")
    public String addGift(Gift gift, String pictureId) {
        boolean res;
        res = pictureId != null;
        String id = null;

        if (res) {
            id = giftService.addGift(gift);

            res = id != null;

            if (res) {
                Picture picture = new Picture();
                picture.setId(pictureId);
                picture.setPositionType(2);
                picture.setSpecificPosition(Integer.parseInt(id));

                res = pictureService.modifyMeaning(picture);
            }
        }

        BaseResult baseResult;
        if (res) {
            AddGiftData data = new AddGiftData();
            data.id = id;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("添加成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("添加失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/admin/modifyGiftInfo")
    public String modifyGiftInfo(Gift gift) {
        boolean res = giftService.modifyGift(gift);
        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("修改成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("修改失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @GetMapping("/admin/removeGift")
    public String removeGift(String id) {
        boolean res = giftService.removeGift(id);
        BaseResult baseResult;

        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("移除成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("移除失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetAllGiftData {
        public List<Gift> giftList;
    }

    @GetMapping("/getAllGift")
    public String getAllGift() {
        List<Gift> gifts = giftService.getAllGift();
        BaseResult baseResult;

        if (gifts != null) {
            GetAllGiftData data = new GetAllGiftData();
            data.giftList = gifts;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }
        
        return JSONUtil.objectToString(baseResult);
    }
}