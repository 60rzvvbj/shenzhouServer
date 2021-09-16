package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ArticleMapper;
import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article getArticle(String id) {
        return articleMapper.getArticleById(id);
    }
}
