package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Article;

import java.util.List;

public interface ArticleService {
    Article getArticle(String id);

    // 通过页数查询
    List<Article> getArticleByPage(int page);

    // 通过省份筛选
    List<Article> getArticleByProvince(String province, int page);

    // 发表文章
    int addArticle(Article article);

    // 修改文章
    boolean modifyArticle(Article article);

    // 删除文章
    boolean removeArticle(String id);
}
