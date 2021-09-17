package com.ycx.shenzhou.mapper;


import com.ycx.shenzhou.pojo.Guideapply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GuideApplyMapper {

    @Insert("insert into guideapply values(null, #{applytime}, #{account}, #{introduction})")
    int addGuideApply(Guideapply guideapply);

    @Delete("delete from guideapply where id = #{id}")
    boolean removeGuideApply(String id);

    @Select("select * from guideapply")
    List<Guideapply> getAllGuideapply();

    @Select("select *from guideapply where id = #{id}")
    Guideapply getGuideapplyById(String id);
}
