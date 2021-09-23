package com.ycx.shenzhou;

import com.ycx.shenzhou.mapper.ParticipateMapper;
import com.ycx.shenzhou.pojo.*;
import com.ycx.shenzhou.service.*;
import com.ycx.shenzhou.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class ShenzhouApplicationTests {

    @Autowired
    private CollageService collageService;

    @Test
    void contextLoads() {
        System.out.println(collageService.getCollage("1"));
    }
}
