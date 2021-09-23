package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.ArticleMapper;
import com.ycx.shenzhou.mapper.ThumbMapper;
import com.ycx.shenzhou.mapper.UserMapper;
import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ThumbMapper thumbMapper;

    @Autowired
    private UserMapper userMapper;

    // 每页的数量
    private final int NUMBER = 10;

    // 打赏对照表
    private final int[] REWARD_COST = {6, 18, 30, 68, 198, 328, 648};
    private final int[] REWARD_INCOME = {1, 4, 8, 20, 60, 110, 240};

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
        res = (int) Math.ceil((0.0 + res) / NUMBER);
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
    public boolean rewardArticle(String account, String id, int quota) {
        int flag = -1;
        for (int i = 0; i < REWARD_COST.length; i++) {
            if (quota == REWARD_COST[i]) {
                flag = i;
            }
        }
        if(flag == -1){
            return false;
        }

        Article article = articleMapper.getArticleById("id"); // 获取到相关文章
        if(article == null) {
             return false;
        }

        User user = userMapper.getUserByAccount(account); // 打赏人
        user.setBalance(user.getBalance() - quota); // 余额减少
        String author_Article = article.getAccount(); // 通过文章ID获取到作者的账号
        User author = userMapper.getUserByAccount(author_Article); // 通过账号获取到作者的对象
        author.setBalance(author.getBalance() + REWARD_INCOME[flag]); // 余额增加
        return userMapper.modifyBalance(user) > 0 && userMapper.modifyBalance(author) > 0;
    }

    @Override
    public List<Integer> getRewardPriceTable() {
        List<Integer> res = new LinkedList<>();
        for (int cost : REWARD_COST) {
            res.add(cost);
        }
        return res;
    }
}
