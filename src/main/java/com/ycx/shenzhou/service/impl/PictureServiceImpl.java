package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.PictureMapper;
import com.ycx.shenzhou.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service("PictureService")
public class PictureServiceImpl implements PictureService {

    private static final String ROOT_FOLDER = "src\\main\\resources\\static\\file\\img\\";
    private static final String RESULT_URL = "file/img/";
    private static final String DEFAULT_FOLDER = "file/default/";

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        Path path = new File("").toPath().resolve(ROOT_FOLDER + fileName);
        try {
            file.transferTo(path.toAbsolutePath().toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RESULT_URL + fileName;
    }

    @Override
    public String getDefaultPictureUrl(int type) {
        if (type == 1) {
            return DEFAULT_FOLDER + "headPortrait.jpg";
        } else if (type == 3) {
            return DEFAULT_FOLDER + "giftPicture.jpg";
        }
        return null;
    }

    @Override
    public String getUserHeadPortraitUrl(String account) {
        return pictureMapper.getUserHeadPortraitUrl(account);
    }


}
