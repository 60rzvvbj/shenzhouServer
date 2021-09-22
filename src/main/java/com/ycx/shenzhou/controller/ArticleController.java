package com.ycx.shenzhou.controller;

import com.ycx.shenzhou.pojo.Article;
import com.ycx.shenzhou.pojo.Picture;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.ArticleService;
import com.ycx.shenzhou.service.PictureService;
import com.ycx.shenzhou.service.UserService;
import com.ycx.shenzhou.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    private static class ReleaseArticleData {
        public String id;
        public long releaseTime;
    }

    @PostMapping("/releaseArticle")
    public String releaseArticle(HttpServletRequest request, Article article, String pictureId) {
        String account = (String) request.getAttribute("account");
        article.setAccount(account);

        System.out.println(JSONUtil.objectToString(article));

        long time = new Date().getTime();
        article.setReleaseTime(new Date().getTime());

        String id = articleService.addArticle(article);

        BaseResult baseResult;

        if (id != null) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("发布成功");

            // 如果有配图
            if (pictureId != null) {

                // 给对应配图附上含义
                Picture picture = new Picture();
                picture.setId(pictureId);
                picture.setPositionType(2);
                picture.setSpecificPosition(Integer.parseInt(id));
                pictureService.modifyMeaning(picture);
            }

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

    @PostMapping("/editArticle")
    public String editArticle(Article article) {
        boolean res = articleService.modifyArticle(article);
        BaseResult baseResult;
        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("修改成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("修改失败");
        }
        return JSONUtil.objectToString(baseResult);
    }

    @GetMapping("/thumbArticle")
    public String thumbArticle(HttpServletRequest request, String articleId, int type) {
        String account = (String) request.getAttribute("account");
        boolean res;
        if (type == 1) {
            res = articleService.addThumb(account, articleId);
        } else {
            res = articleService.cancelThumb(account, articleId);
        }
        BaseResult baseResult;
        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
        } else {
            baseResult = BaseResult.getErrorBaseData();
        }
        return JSONUtil.objectToString(baseResult);
    }


    private static class GetArticlesData {
        public static class ArticleData {
            public String id;
            public String title;
            public String placeName;
            public String province;
            public long releaseTime;
            public String content;
            public String authorUsername;
            public int thumb;
        }

        public List<ArticleData> articles;
        public int pageCount;
    }

    @GetMapping("/public/getArticles")
    public String getArticles(int page, String province) {
        BaseResult baseResult;
        List<Article> articles;

        // 判断是否需要通过省份筛选
        if (province != null) {
            articles = articleService.getArticleByProvince(province, page);
        } else {
            articles = articleService.getArticleByPage(page);
        }

        if (articles != null) {
            GetArticlesData getArticlesData = new GetArticlesData();
            getArticlesData.articles = new LinkedList<GetArticlesData.ArticleData>();

            // 遍历赋值
            for (Article article : articles) {
                GetArticlesData.ArticleData articleData = new GetArticlesData.ArticleData();

                articleData.id = article.getId();
                articleData.title = article.getTitle();
                articleData.placeName = article.getPlaceName();
                articleData.province = article.getProvince();
                articleData.releaseTime = article.getReleaseTime();
                articleData.content = article.getContent();
                articleData.thumb = article.getThumb();
                articleData.authorUsername = userService.getUserInfo(article.getAccount()).getUsername();

                getArticlesData.articles.add(articleData);
            }

            // 总页数
            getArticlesData.pageCount = articleService.getPageCount(province);

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("获取成功");
            baseResult.setData(getArticlesData);
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    private static class GetArticleData {
        public String title;
        public String placeName;
        public String province;
        public long releaseTime;
        public String content;
        public String authorAccount;
        public String authorUsername;
        public int thumb;
        public boolean isThumb;
    }

    @GetMapping("/getArticle")
    public String getArticle(HttpServletRequest request, String id) {
        String account = (String) request.getAttribute("account");
        Article article = articleService.getArticle(id);

        BaseResult baseResult;

        if (article != null) {

            // 结果集赋值
            GetArticleData articleData = new GetArticleData();
            articleData.title = article.getTitle();
            articleData.placeName = article.getPlaceName();
            articleData.province = article.getProvince();
            articleData.releaseTime = article.getReleaseTime();
            articleData.content = article.getContent();
            articleData.authorAccount = article.getAccount();
            articleData.authorUsername = userService.getUserInfo(article.getAccount()).getUsername();
            articleData.thumb = article.getThumb();
            articleData.isThumb = articleService.isThumb(account, id);

            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setData(articleData);
            baseResult.setMessage("获取成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("获取失败");
        }

        return JSONUtil.objectToString(baseResult);
    }

    @PostMapping("/rewardArticle")
    public String rewardArticle(HttpServletRequest request, String quota, String id) {
        String account = (String) request.getAttribute("account");

        boolean res = articleService.rewardArticle(account, id);

        BaseResult baseResult;
        if (res) {
            baseResult = BaseResult.getSuccessBaseData();
            baseResult.setMessage("打赏成功");
        } else {
            baseResult = BaseResult.getErrorBaseData();
            baseResult.setMessage("打赏失败");
        }

        return JSONUtil.objectToString(baseResult);
    }
}
