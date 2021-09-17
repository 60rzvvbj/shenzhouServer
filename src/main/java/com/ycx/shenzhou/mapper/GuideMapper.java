package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Guide;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GuideMapper {

    @Select("select * from guide where id = #{id}")
    Guide getGuideById(String id);

    @Select("select * from guide where account = #{account}")
    Guide getGuideByAccount(String account);

    @Insert("insert into guide values(null, #{account}, #{introduction})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addGuide(Guide guide);

    @Update("update guide set introduction = #{introduction} where id = #{id}")
    int modifyGuide(Guide guide);

    @Select("select * from guide")
    List<Guide> getAllGuide();

}
