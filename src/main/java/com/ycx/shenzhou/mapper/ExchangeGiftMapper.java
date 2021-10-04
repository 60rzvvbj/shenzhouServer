package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.ExchangeGift;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExchangeGiftMapper {
    @Select("select * from exchangegift where id = #{id}")
    @Results(value = {@Result (column = "etime", property = "eTime"),
            @Result (column = "state", property = "status")})
    ExchangeGift getExchangeGiftById(String id);

    @Select("select account from exchangegift where gid = #{gid}")
    @Results(value = {@Result (column = "etime", property = "eTime"),
            @Result (column = "state", property = "status")})
    List<String> getExchangeGiftAccountByGid(String gid);

    @Insert("insert into exchangegift values(null, #{account}, #{gid}, #{eTime}, #{address}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addExchangeGift(ExchangeGift exchangegift);

    @Delete("delete from exchangegift where id = #{id}")
    boolean removeExchangeGift(String id);

    @Update("update exchangegift set state = #{status} where id = #{id}")
    int modifyExchangeGift(ExchangeGift exchangegift);

    @Select("select * from exchangegift where state = #{status}")
    @Results(value = {@Result (column = "etime", property = "eTime"),
            @Result (column = "state", property = "status")})
    List<ExchangeGift> getAllExchangeGift(int status);

    @Select("select * from exchangegift where account = #{account}")
    @Results(value = {@Result (column = "etime", property = "eTime"),
            @Result (column = "state", property = "status")})
    List<ExchangeGift> getExchangeGiftByAccount(String account);

    @Select("select * from exchangegift where account = #{account} and gid = #{gid}")
    @Results(value = {@Result (column = "etime", property = "eTime"),
            @Result (column = "state", property = "status")})
    ExchangeGift getExchangeGiftByAccountGid(String account, String gid);
}
