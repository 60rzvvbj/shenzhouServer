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
        Guide guide = new Guide();
        guide.setId("2");
        guide.setIntroduction("非常优秀");
        System.out.println(guideService.modifyGuide(guide));
    }

}
