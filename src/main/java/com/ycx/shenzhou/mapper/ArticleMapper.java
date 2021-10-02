package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleMapper {

    @Select("select article.*, if(thumb is null, 0, thumb)\n" +
            "from article \n" +
            "left join (\n" +
            "\tselect aid, count(aid) as thumb\n" +
            "\tfrom thumb\n" +
            ") as t\n" +
            "on id = aid\n" +
            "where id = #{id};")
    Article getArticleById(String id);

    @Select("select article.*, if(thumb is null, 0, thumb)\n" +
            "from article\n" +
            "left join (\n" +
            "\tselect aid, count(aid) as thumb\n" +
            "\tfrom thumb\n" +
            "\tgroup by aid\n" +
            ") as t\n" +
            "on id = aid\n" +
            "limit #{page}, #{number};")
    List<Article> getArticleByPage(int page, int number);


    @Select("select article.*, if(thumb is null, 0, thumb)\n" +
            "from article\n" +
            "left join (\n" +
            "\tselect aid, count(aid) as thumb\n" +
            "\tfrom thumb\n" +
            "\tgroup by aid\n" +
            ") as t\n" +
            "on id = aid\n" +
            "where province = #{province}\n" +
            "limit #{page}, #{number};")
    List<Article> getArticleByProvince(String province, int page, int number);

    @Insert("insert into article values(null, #{title}, #{placeName}, #{province}, #{releaseTime}, #{content}, #{account});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addArticle(Article article);

    @Update("update article set title = #{title}, placename = #{placeName}, province = #{province}, content = #{content} where id = #{id}")
    int modifyArticle(Article article);

    @Delete("delete from article where id = #{id}")
    int removeArticle(String id);

    @Select("select count(*) from article")
    int getPageCount();

    @Select("select count(*) from article where province = #{province}")
    int getPageCountByProvince(String province);

    @Select("select * from article")
    List<Article> getAllArticle();
}
