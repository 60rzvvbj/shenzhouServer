package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ArticleMapper;
import com.ycx.shenzhou.mapper.ThumbMapper;
import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ThumbMapper thumbMapper;

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
    public int getPageCount(String province) {
        int res;
        if (province != null) {
            res = articleMapper.getPageCountByProvince(province);
        } else {
            res = articleMapper.getPageCount();
        }
        res = (int)Math.ceil((0.0 + res) / NUMBER);
        return res;
    }

    @Override
    public String addArticle(Article article) {
        article.setReleaseTime(new Date().getTime());
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

    @Override
    public boolean addThumb(String account, String id) { // 给文章点赞
        return thumbMapper.addThumb(account, id) > 0; // 在数据库中添加点赞记录
    }

    @Override
    public boolean cancelThumb(String account, String id) { // 取消点赞
        return thumbMapper.removeThumb(account, id); // 在数据库中删除点赞记录
    }

    @Override
    public boolean isThumb(String account, String id) {
        return thumbMapper.isThumb(account, id) > 0;
    }

    @Override
    public boolean rewardArticle(String account, String id) {

        return false;
    }
}
