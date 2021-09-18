package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.ExchangeGift;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExchangeGiftMapper {
    @Select("select * from exchangegift where id = #{id}")
    ExchangeGift getExchangegiftById(String id);

    @Insert("insert into exchangegift values(null, #{account}, #{gid}, #{etime}, #{address}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addExchangegift(ExchangeGift exchangegift);

    @Delete("delete from exchangegift where id = #{id}")
    boolean removeExchangegift(String id);

    @Update("update gift set address= #{address}, state = #{state} where id = #{id}")
    int modifyExchangegift(ExchangeGift exchangegift);

    @Select("select * from Exchangegift")
    List<ExchangeGift> getAllExchangegift();
}
