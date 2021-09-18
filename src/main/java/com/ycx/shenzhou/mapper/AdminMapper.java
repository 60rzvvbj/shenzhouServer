package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminMapper {
    @Insert("insert into admin values(null, #{account})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addAdmin(Admin admin);

    @Delete("delete from admin where id = #{id}")
    boolean removeAdmin(String id);

    @Select("select * from admin")
    List<Admin> getAllAdmin();

    @Select("select * from admin where id = #{id}")
    Admin getAdminById(String id);

    @Select("select * from admin where account = #{account}")
    Admin getAdminByAccount(String account);
}