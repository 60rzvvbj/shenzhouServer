package com.ycx.shenzhou.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ThumbMapper {

    @Insert("insert into picture values(#{account}, #{aid})")
    int addThumb(String account, String aid);

    @Delete("delete from picture where account = #{account}, aid = #{aid}")
    boolean removeThumb(String account, String aid);

}
