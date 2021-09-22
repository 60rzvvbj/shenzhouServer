package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Collage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollageMapper {
    @Select("select * from collage where id = #{id}")
    @Results(value = {@Result(column = "describes", property = "describe"),
            @Result(column = "pnumber", property = "pNumber"),
            @Result(column = "dtime", property = "dTime")})
    Collage getCollageById(String id);

    @Insert("insert into collage values(null, #{account}, #{pNumber}, #{departure}, #{destination}, #{dTime}, #{describe})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addCollage(Collage collage);

    @Delete("delete from collage where id = #{id}")
    boolean removeCollage(String id);

    @Select("select * from collage where account = #{account}")
    @Results(value = {@Result(column = "describes", property = "describe"),
                     @Result(column = "pnumber", property = "pNumber"),
                     @Result(column = "dtime", property = "dTime")})
    List<Collage> getCollageByAccount(String account);

    @Select("select * from collage")
    @Results(value = {@Result(column = "describes", property = "describe"),
            @Result(column = "pnumber", property = "pNumber"),
            @Result(column = "dtime", property = "dTime")})
    List<Collage> getAllCollage();
}
