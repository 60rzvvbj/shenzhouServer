package com.ycx.shenzhou.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ParticipateMapper {
    @Insert("insert into participate values(#{account}, #{cid})")
    int addParticipate(String account, String cid); // 添加拼团申请

    @Select("select cid from participate where cid = #{id}")
    List<String> getPaticipateByAccount(String account);

    @Select("select account from participate where cid = #{id}") // 通过拼团ID查
    List<String> getPaticipateById(String id);

    @Delete("delete from participate where cid = #{id}") // 通过拼团ID删
    boolean removePaticipateById(String id);
}
