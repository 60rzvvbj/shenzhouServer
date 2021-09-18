package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Participate;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ParticipateMapper {
    @Select("select * from participate where account = #{account}")
    Participate getParticipateById(String account);

    @Insert("insert into participate values(#{account}, #{cid})")
    int addParticipate(Participate participate);

    @Delete("delete from participate where account = #{account}")
    boolean removeParticipate(String account);

    @Update("update gift set cid = #{cid} where account = #{account}")
    int modifyParticipate(Participate participate);

    @Select("select * from participate")
    List<Participate> getAllParticipate();
}
