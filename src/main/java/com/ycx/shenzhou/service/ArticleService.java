package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Article;

import java.util.List;

public interface ArticleService {
    // 通过文章id查询
    Article getArticle(String id);

    // 通过页数查询
    List<Article> getArticleByPage(int page);

    // 通过省份筛选
    List<Article> getArticleByProvince(String province, int page);

    // 获取文章个数
    int getPageCount(String province);

    // 发表文章
    String addArticle(Article article);

    // 修改文章
    boolean modifyArticle(Article article);

    // 删除文章
    boolean removeArticle(String id);

    // 给文章点赞
    boolean addThumb(String account, String id);

    // 取消点赞
    boolean cancelThumb(String account, String id);

    // 是否点了赞
    boolean isThumb(String account, String id);
}
