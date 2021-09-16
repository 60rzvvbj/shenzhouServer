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

    @Override
    public Article getArticle(String id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public List<Article> getArticleByPage(int page) {
        return null;
    }

    @Override
    public List<Article> getArticleByProvince(String province, int page) {
        return null;
    }

    @Override
    public int addArticle(Article article) {
        return 0;
    }

    @Override
    public boolean modifyArticle(Article article) {
        return false;
    }

    @Override
    public boolean removeArticle(String id) {
        return false;
    }
}
