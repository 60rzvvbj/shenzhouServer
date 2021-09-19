package com.ycx.shenzhou.controller;


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

    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request) throws IOException {
        MultipartFile img = ((MultipartHttpServletRequest) request).getFile("file");

        BaseResult baseResult = BaseResult.getSuccessBaseData();
        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/uploadHeadPortrait")
    public String uploadHeadPortrait(HttpServletRequest request) {
        String account = (String) request.getAttribute("account");

        return "";
    }

}
