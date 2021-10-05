package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface PictureService {

    // 获取随机文件名
    String getRandomFileName();

    // 添加文件
    boolean uploadFile(MultipartFile file, String fileName, Picture picture);

    // 修改图片含义
    boolean modifyMeaning(Picture picture);

    // 获取默认图片路径，参数图片类型
    String getDefaultPictureUrl(int type);

    // 获取用户头像路径
    Picture getUserHeadPortrait(String account);

    // 获取礼物图片路径
    Picture getGiftPhoto(String id);

    // 获取文章图片路径
    Picture getArticlePhoto(String id);

    // 通过ID获取图片信息
    Picture getPicture(String id);

    // 获取文件
    File getFile(String url);

    // 删除图片
    boolean removePicture(String id);

}
