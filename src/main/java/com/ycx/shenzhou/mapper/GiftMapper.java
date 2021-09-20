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
    Gift getGiftById(String id);

    @Insert("insert into gift values(null, #{name}, #{describe}, #{price}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addGift(Gift gift);

    @Delete("delete from gift where id = #{id}")
    boolean removeGift(String id);

    @Update("update gift set name = #{name}, describe = #{describe}, price = #{price} where id = #{id}")
    int modifyGift(Gift gift);

    @Update("update gift set status = #{status} where id = #{id}")
    int modifyGiftStatus(Gift gift);

    @Select("select * from gift")
    List<Gift> getAllGift();

    @Select("select * from gift where status = 0")
    List<Gift> getAllNotExchange();
}
