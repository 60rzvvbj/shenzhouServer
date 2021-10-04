package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Gift;
import com.ycx.shenzhou.pojo.Guide;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GiftMapper {
    @Select("select * from gift where id = #{id}")
    @Result(column = "describes", property = "describe")
    Gift getGiftById(String id);

    @Insert("insert into gift values(null, #{name}, #{describe}, #{price}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addGift(Gift gift);

    @Delete("delete from gift where id = #{id}")
    boolean removeGift(String id);

    @Update("update gift set name = #{name}, describes = #{describe}, price = #{price} where id = #{id}")
    int modifyGift(Gift gift);

    @Update("update gift set status = #{status} where id = #{id}")
    int modifyGiftStatus(Gift gift);

    @Select("select * from gift")
    @Result(column = "describes", property = "describe")
    List<Gift> getAllGift();

    @Select("select * from gift where status = #{status}")
    @Result(column = "describes", property = "describe")
    List<Gift> getAllNotExchange(int status);
}
