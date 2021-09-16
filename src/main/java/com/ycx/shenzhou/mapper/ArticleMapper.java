package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleMapper {

    @Select("select * from article, (select count(*) as thumb from thumb where #{id} = aid) as t where id = #{id};")
    @Result(column = "thumb", property = "thumb")
    Article getArticleById(String id);

}
