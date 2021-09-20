package com.ycx.shenzhou.controller;


import com.ycx.shenzhou.pojo.Picture;
import com.ycx.shenzhou.service.PictureService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
public class FileController {

    @Autowired
    private PictureService pictureService;

    private static class UploadFileData {
        public String id;
        public String url;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request) throws IOException {
        MultipartFile img = ((MultipartHttpServletRequest) request).getFile("file");

        Picture picture = new Picture();
        String fileName = pictureService.getRandomFileName();
        String url = pictureService.uploadFile(img, fileName, picture);

        BaseResult baseResult;
        if (url != null) {
            UploadFileData data = new UploadFileData();
            data.id = picture.getId();
            data.url = url;

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("上传成功");
            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("上传失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/uploadHeadPortrait")
    public String uploadHeadPortrait(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");

        return "";
    }

}
