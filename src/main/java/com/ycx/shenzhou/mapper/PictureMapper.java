package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Picture;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PictureMapper {
    @Select("select * from picture where id = #{id}")
    Picture getPictureById(String id);

    @Insert("insert into picture values(null, #{url}, #{positionType}, #{specificPosition})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addPicture(Picture picture);

    @Delete("delete from picture where id = #{id}")
    int removePicture(String id);

    @Update("update picture set positiontype = #{positionType}, specificposition = #{specificPosition} where id = #{id}")
    int modifyMeaning(Picture picture);

    @Select("select * from picture where positiontype = 1 and specificposition = #{token}")
    Picture getUserHeadPortrait(String token);

    @Select("select * from picture where positiontype = 2 and specificposition = #{aid}")
    Picture getArticlePicture(String aid);

    @Select("select * from picture where positiontype = 3 and specificposition = #{gid}")
    Picture getGiftPicture(String gid);

    @Select("select count(*) from picture where url = #{url}")
    int urlIsExist(String url);
}
