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

    @Insert("insert into picture values(null, #{url}, #{positiontype}, #{positiontype}, #{positiontype}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addPicture(Picture picture);

    @Delete("delete from picture where id = #{id}")
    boolean removePicture(String id);

    @Update("update picture set url= #{url}, positiontype = #{positiontype}, specificposition = #{specificposition} where id = #{id}")
    int modifyPicture(Picture picture);

    @Select("select * from picture")
    List<Picture> getAllPicture();
}
