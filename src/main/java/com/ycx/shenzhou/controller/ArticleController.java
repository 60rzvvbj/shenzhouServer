package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.service.ArticleService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    private static class ReleaseArticleData{
        public String id;
        public long releaseTime;
    }

    @PostMapping("/releaseArticle")
    public String releaseArticle(HttpServletRequest request, Article article, String pictureId){
        String account = (String) request.getAttribute("account");
        article.setAccount(account);

        System.out.println(JSONUtil.objectToString(article));

        long time = new Date().getTime();
        article.setReleasetime(new Date().getTime());

        String id = articleService.addArticle(article);

        BaseResult baseResult;

        if (id != null) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("发布成功");

            ReleaseArticleData data = new ReleaseArticleData();
            data.id = id;
            data.releaseTime = time;

            baseResult.setData(data);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("发布失败");
        }


        return JSONUtil.objectToString(baseResult);
    }

}
