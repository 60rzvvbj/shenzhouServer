package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ArticleMapper;
import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    // 每页的数量
    private final int NUMBER = 10;

    @Override
    public Article getArticle(String id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public List<Article> getArticleByPage(int page) {
        return articleMapper.getArticleByPage(page * NUMBER, NUMBER);
    }

    @Override
    public List<Article> getArticleByProvince(String province, int page) {
        return articleMapper.getArticleByProvince(province, page * NUMBER, NUMBER);
    }

    @Override
    public String addArticle(Article article) {
        articleMapper.addArticle(article);
        return article.getId();
    }

    @Override
    public boolean modifyArticle(Article article) {
        return articleMapper.modifyArticle(article) > 0;
    }

    @Override
    public boolean removeArticle(String id) {
        return articleMapper.removeArticle(id) > 0;
    }
}
