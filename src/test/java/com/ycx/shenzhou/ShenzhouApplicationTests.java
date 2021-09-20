package com.ycx.shenzhou;

import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.service.ArticleService;
import com.ycx.shenzhou.service.GuideService;
import com.ycx.shenzhou.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ShenzhouApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private GuideService guideService;

    @Test
    void contextLoads() {
    }

}
