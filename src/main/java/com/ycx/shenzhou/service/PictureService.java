package com.ycx.shenzhou.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    // 添加文件
    String uploadFile(MultipartFile file, String fileName);

    // 获取默认图片路径，参数图片类型
    String getDefaultPictureUrl(int type);

    // 获取用户头像路径
    String getUserHeadPortraitUrl(String account);


}
