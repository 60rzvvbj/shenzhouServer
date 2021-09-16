package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleMapper {

    @Select("select * from article where id = #{id}")
    Article getArticleById(String id);

}
