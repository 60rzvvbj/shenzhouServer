package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.ExchangeGift;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExchangeGiftMapper {
    @Select("select * from exchangegift where id = #{id}")
    ExchangeGift getExchangeGiftById(String id);

    @Insert("insert into exchangegift values(null, #{account}, #{gid}, #{etime}, #{address}, null")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addExchangeGift(ExchangeGift exchangegift);

    @Delete("delete from exchangegift where id = #{id}")
    boolean removeExchangeGift(String id);

    @Update("update gift set address= #{address}, state = #{state} where id = #{id}")
    int modifyExchangeGift(ExchangeGift exchangegift);

    @Select("select * from Exchangegift")
    List<ExchangeGift> getAllExchangeGift();

    @Select("select * from Exchangegift where account = #{account}")
    List<ExchangeGift> getExchangeGiftByAccount(String account);
}
