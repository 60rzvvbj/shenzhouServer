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
        Article article = new Article();
        article.setAccount("cht");
        article.setTitle("asd");
        article.setPlaceName("qwe");
        article.setProvince("广东");
        article.setContent("qwe好");
        String id = articleService.addArticle(article);
        System.out.println(id);
    }

}
