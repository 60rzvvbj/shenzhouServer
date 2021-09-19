package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Consult;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConsultMapper {
    @Insert("insert into consult values(null, #{account}, #{gid}, #{consulttime}, #{content}" +
            ", null, null, #{stage})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addConsult(Consult consult);

    @Delete("delete from consult where id = #{id}")
    boolean removeConsult(String id);

    @Select("select * from consult")
    List<Consult> getAllConsult();

    @Select("select * from consult where id = #{id}")
    Consult getConsultById(String id);

    @Update("update consult set reply = #{reply}, stage = #{stage} where id = #{id}")
    int modifyConsultReply(Consult consult);

    @Update("update consult set score = #{score}, stage = #{stage} where id = #{id}")
    int modifyConsultScore(Consult consult);

    @Select("select * from consult where gid = #{gid}")
    List<Consult> getConsultByGid(String gid);

    @Select("select * from consult where account = #{account}")
    List<Consult> getConsultByAccount(String account);
}
