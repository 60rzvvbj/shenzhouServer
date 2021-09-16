package com.ycx.shenzhou;

import com.ycx.shenzhou.service.ArticleService;
import com.ycx.shenzhou.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShenzhouApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    void contextLoads() {
        System.out.println(JSONUtil.objectToString(articleService.getArticle("1")));
    }

}
