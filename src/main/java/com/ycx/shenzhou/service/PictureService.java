package com.ycx.shenzhou.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    // 添加文件
    String uploadFile(MultipartFile file, String fileName);

}
