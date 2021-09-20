package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Experience;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExperienceMapper {
    @Select("select * from experience where account = #{account}")
    Experience getExperienceByAccount(String account);

    @Insert("insert into experience values(#{account}, #{value}, #{level}")
    int addExperience(Experience experience);

    @Delete("delete from experience where account = #{account}")
    boolean removeExperience(String account);

    @Update("update gift set value = #{value}, level = #{level} where account = #{account}")
    int modifyExperience(Experience experience);

    @Select("select * from Experience")
    List<Experience> getAllExperience();
}
