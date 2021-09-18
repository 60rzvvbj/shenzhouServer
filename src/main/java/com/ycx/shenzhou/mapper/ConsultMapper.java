package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Consult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConsultMapper {
    @Insert("insert into consult values(null, #{account}, #{gid}, #{consulttime}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addConsult(Consult consult);

    @Delete("delete from consult where id = #{id}")
    boolean removeConsult(String id);

    @Select("select * from consult")
    List<Consult> getAllconsult();

    @Select("select * from consult where id = #{id}")
    Consult getconsultById(String id);

    @Update("update consult set reply = #{reply} where id = #{id}")
    int modifyConsultReply(Consult consult);

    @Update("update consult set score = #{score} where id = #{id}")
    int modifyConsultScore(Consult consult);
}
