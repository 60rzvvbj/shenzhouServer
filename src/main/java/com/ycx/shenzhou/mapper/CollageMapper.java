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
    int addCollage(CollageMapper collage);

    @Delete("delete from collage where id = #{id}")
    boolean removeCollage(String id);

    @Update("update gift set account = #{account}, pnumber = #{pnumber}, departure = #{departure}, destination = #{destination}, dtime = #{dtime}, describe = #{describe} where id = #{id}")
    int modifyCollage(Collage collage);

    @Select("select * from collage")
    List<Collage> getAllCollage();
}
