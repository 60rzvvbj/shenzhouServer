package com.ycx.shenzhou.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ThumbMapper {

    @Insert("insert into thumb values(#{account}, #{aid})")
    int addThumb(String account, String aid);

    @Delete("delete from thumb where account = #{account} and aid = #{aid}")
    boolean removeThumb(String account, String aid);

    @Select("select count(*) from thumb where account = #{account} and aid = #{aid}")
    int isThumb(String account, String aid);
}
