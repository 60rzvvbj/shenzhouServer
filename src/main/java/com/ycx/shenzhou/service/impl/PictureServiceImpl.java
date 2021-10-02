package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.PictureMapper;
import com.ycx.shenzhou.pojo.Picture;
import com.ycx.shenzhou.service.PictureService;
import com.ycx.shenzhou.util.JSONUtil;
import com.ycx.shenzhou.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service("PictureService")
public class PictureServiceImpl implements PictureService {

    private static final String ROOT_FOLDER = "data\\file\\img\\";
    private static final String RESULT_URL = "file/img/";
    private static final String DEFAULT_FOLDER = "file/default/";
    private static final int RANDOM_FILE_NAME_LENGTH = 15;
    private static final char[] BASE_CHARACTER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public String getRandomFileName() {
        String fileName;
        do {
            fileName = createRandomFileName();
        } while (fileIsExist(fileName));
        return fileName;
    }

    private boolean fileIsExist(String fileName) {
        return pictureMapper.urlIsExist(getUrl(fileName)) > 0;
    }

    private String createRandomFileName() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < RANDOM_FILE_NAME_LENGTH; i++) {
            int randIndex = RandomUtil.randomInt(0, BASE_CHARACTER.length - 1);
            res.append(BASE_CHARACTER[randIndex]);
        }
        return res.toString();
    }

    private String getUrl(String fileName) {
        return RESULT_URL + fileName;
    }

    private String getFileName(String url) {
        return url.substring(RESULT_URL.length());
    }

    @Override
    public boolean uploadFile(MultipartFile file, String fileName, Picture picture) {
        if (fileName == null && picture.getUrl() != null) {
            fileName = getFileName(picture.getUrl());
            picture.setPositionType(0);
        }
        Path path = new File("").toPath().resolve(ROOT_FOLDER + fileName);
        try {
            file.transferTo(path.toAbsolutePath().toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (fileIsExist(fileName)) {
            return true;
        }
        picture.setUrl(getUrl(fileName));
        pictureMapper.addPicture(picture);
        return true;
    }

    @Override
    public boolean modifyMeaning(Picture picture) {
        return pictureMapper.modifyMeaning() > 0;
    }

    @Override
    public String getDefaultPictureUrl(int type) {
        if (type == 1) {
            return DEFAULT_FOLDER + "headPortrait.jpg";
        } else if (type == 3) {
            return DEFAULT_FOLDER + "giftPicture.png";
        }
        return null;
    }

    @Override
    public String getUserHeadPortraitUrl(String token) {
        return pictureMapper.getUserHeadPortraitUrl(token);
    }

    @Override
    public String getGiftPhotoUrl(String id) {
        return pictureMapper.getGiftPicture(id);
    }

    @Override
    public Picture getPicture(String id) {
        return null;
    }

    @Override
    public File getFile(String url) {
        return new File("data\\" + url);
    }

}
