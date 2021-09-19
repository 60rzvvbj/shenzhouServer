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

    @Insert("insert into picture values(null, #{url}, #{positiontype}, #{specificposition}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addPicture(Picture picture);

    @Delete("delete from picture where id = #{id}")
    boolean removePicture(String id);

    @Select("select url from picture where positiontype = 1 and specificposition = #{account}")
    String getUserHeadPortraitUrl(String account);

    @Select("select url from picture where positiontype = 2 and specificposition = #{aid}")
    String getArticlePicture(String aid);

    @Select("select url from picture where positiontype = 3 and specificposition = #{gid}")
    String getGiftPicture(String gid);
}
