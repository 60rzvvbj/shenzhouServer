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

    @Select("select account from exchangegift where gid = #{gid}")
    List<String> getExchangeGiftAccountByGid(String gid);

    @Insert("insert into exchangegift values(null, #{account}, #{gid}, #{eTime}, #{address}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addExchangeGift(ExchangeGift exchangegift);

    @Delete("delete from exchangegift where id = #{id}")
    boolean removeExchangeGift(String id);

    @Update("update exchangegift set state = #{status} where id = #{id}")
    int modifyExchangeGift(ExchangeGift exchangegift);

    @Select("select * from Exchangegift")
    @Results(value = {@Result (column = "etime", property = "eTime"),
            @Result (column = "state", property = "status")})
    List<ExchangeGift> getAllExchangeGift();

    @Select("select * from Exchangegift where account = #{account}")
    List<ExchangeGift> getExchangeGiftByAccount(String account);
}
