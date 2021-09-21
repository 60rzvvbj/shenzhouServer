package com.ycx.shenzhou.mapper;


import com.ycx.shenzhou.pojo.Guide;
import com.ycx.shenzhou.pojo.GuideApply;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GuideApplyMapper {

    @Insert("insert into guideapply values(null, #{applytime}, #{account}, #{introduction})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addGuideApply(GuideApply guideApply);

    @Delete("delete from guideapply where id = #{id}")
    boolean removeGuideApply(String id);

    @Select("select * from guideapply")
    List<GuideApply> getAllGuideApply();

    @Select("select * from guideapply where id = #{id}")
    GuideApply getGuideApplyById(String id);

    @Select("select * from guideapply where account = #{account}")
    GuideApply getGuideApplyByAccount(String account);
}
