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

import java.util.LinkedList;
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
                picture.setPositionType(3);
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
        public List<String> idList;
    }

    @GetMapping("/getAllGift")
    public String getAllGift() {
        List<Gift> gifts = giftService.getAllGift();
        BaseResult baseResult;

        if (gifts != null) {
            List<String> idList = new LinkedList<>();
            for (Gift gift : gifts) {
                idList.add(gift.getId());
            }
            GetAllGiftData data = new GetAllGiftData();
            data.idList = idList;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetGiftInfoData {
        public String name;
        public String describe;
        public int price;
        public String photo;
    }

    @GetMapping("/getGiftInfo")
    public String getGiftInfo(String id) {
        Gift gift = giftService.getGift(id);

        BaseResult baseResult;
        if (gift != null) {
            GetGiftInfoData data = new GetGiftInfoData();
            data.name = gift.getName();
            data.describe = gift.getDescribe();
            data.price = gift.getPrice();

            Picture photo = pictureService.getGiftPhoto(gift.getId());
            String photoUrl = photo != null ? photo.getUrl() : null;
            data.photo = photoUrl != null ? photoUrl : pictureService.getDefaultPictureUrl(3);

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
