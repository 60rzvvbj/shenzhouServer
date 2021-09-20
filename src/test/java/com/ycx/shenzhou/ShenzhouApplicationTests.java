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
        article.setAccount("1808078515");
        article.setTitle("真可以");
        article.setPlacename("广州塔");
        article.setProvince("广东");
        article.setReleasetime(new Date().getTime());
        article.setContent("广州塔真可以");

        System.out.println(articleService.addArticle(article));
    }

}
