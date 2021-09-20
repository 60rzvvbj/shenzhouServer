package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Picture;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    // 获取随机文件名
    String getRandomFileName();

    // 添加文件
    String uploadFile(MultipartFile file, String fileName, Picture picture);

    // 获取默认图片路径，参数图片类型
    String getDefaultPictureUrl(int type);

    // 获取用户头像路径
    String getUserHeadPortraitUrl(String account);


}
