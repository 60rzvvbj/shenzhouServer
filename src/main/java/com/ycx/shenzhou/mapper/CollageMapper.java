package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Collage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollageMapper {
    @Select("select * from collage where id = #{id}")
    Collage getCollageById(String id);

    @Insert("insert into collage values(null, #{account}, #{pnumber}, #{departure}, #{destination}, #{dtime}, #{describe})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addCollage(Collage collage);

    @Delete("delete from collage where id = #{id}")
    boolean removeCollage(String id);

    @Select("select * from collage where account = #{account}")
    List<Collage> getCollageByAccount(String account);

    @Select("select * from collage")
    List<Collage> getAllCollage();
}
