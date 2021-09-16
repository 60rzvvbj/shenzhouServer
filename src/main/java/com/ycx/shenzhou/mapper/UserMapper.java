package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where account = #{account}")
    User getUserByAccount(String account);

}
