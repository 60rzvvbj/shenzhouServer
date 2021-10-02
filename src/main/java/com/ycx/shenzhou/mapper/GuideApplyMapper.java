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

    @Insert("insert into guideapply values(null, #{applyTime}, #{account}, #{introduction}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addGuideApply(GuideApply guideApply);

    @Delete("delete from guideapply where id = #{id}")
    boolean removeGuideApply(String id);

    @Update("update guideapply set status = #{status} where id = #{id}")
    int modifyGiftApplyStatus(GuideApply guideApply);

    @Select("select * from guideapply where status = #{status}")
    @Result(column = "applytime", property = "applyTime")
    List<GuideApply> getAllGuideApply(int status);

    @Select("select * from guideapply where id = #{id}")
    @Result(column = "applytime", property = "applyTime")
    GuideApply getGuideApplyById(String id);

    @Select("select * from guideapply where account = #{account}")
    @Result(column = "applytime", property = "applyTime")
    GuideApply getGuideApplyByAccount(String account);
}
