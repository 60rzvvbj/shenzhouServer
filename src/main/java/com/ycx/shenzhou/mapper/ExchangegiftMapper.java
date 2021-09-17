package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Exchangegift;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExchangegiftMapper {
    @Select("select * from exchangegift where id = #{id}")
    Exchangegift getExchangegiftById(String id);

    @Insert("insert into exchangegift values(null, #{account}, #{gid}, #{etime}, #{address}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addExchangegift(Exchangegift exchangegift);

    @Delete("delete from exchangegift where id = #{id}")
    boolean removeExchangegift(String id);

    @Update("update gift set address= #{address}, state = #{state} where id = #{id}")
    int modifyExchangegift(Exchangegift exchangegift);

    @Select("select * from Exchangegift")
    List<Exchangegift> getAllExchangegift();
}
