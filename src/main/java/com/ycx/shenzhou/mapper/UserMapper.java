package com.ycx.shenzhou.mapper;

import com.ycx.shenzhou.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user values(#{account}, #{pwd}, #{username}, #{other}, 0)")
    int addUser(User user);

    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where account = #{account}")
    User getUserByAccount(String account);

    @Update("update user set pwd = #{pwd} where account = #{account}")
    int changePwd(String account, String pwd);

    @Update("update user set balance = #{balance} where account = #{account}")
    int modifyBalance(User user);

    @Update("update user set username = #{username}, other = #{other} where account = #{account}")
    int modifyUserInfo(User user);

}
