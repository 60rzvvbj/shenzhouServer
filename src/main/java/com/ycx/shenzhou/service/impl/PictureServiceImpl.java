package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service("PictureService")
public class PictureServiceImpl implements PictureService {

    private static final String ROOT_FOLDER = "src\\main\\resources\\static\\file\\img\\";
    private static final String RESULT_URL = "file/img/";

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


}
