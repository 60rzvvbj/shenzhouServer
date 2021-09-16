package com.ycx.shenzhou;

import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.service.ArticleService;
import com.ycx.shenzhou.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ShenzhouApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    void contextLoads() {
        Article article = new Article();
        article.setId("3");
        article.setPlacename("广州塔");
        article.setTitle("真可以");
        article.setReleasetime(new Date().getTime());
        article.setProvince("广东");
        article.setContent("广州塔真可以");
        article.setAccount("1808078515");
        article.setThumb(0);
        System.out.println(articleService.modifyArticle(article));
//        System.out.println(articleService.addArticle(article));
//        System.out.println(JSONUtil.objectToString(article));
//        System.out.println(articleService.removeArticle("5"));
//        System.out.println(JSONUtil.objectToString(articleService.getArticleByProvince("广东", 0)));
    }

}
