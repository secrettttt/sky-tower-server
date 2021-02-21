package com.skytower.dao;

import com.skytower.entry.UserEntry;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user_table where username = #{e.username} and password = #{e.password}")
    List<UserEntry> checkPermission(@Param("e") UserEntry e);

    @Select("select * from user_table where user_id = #{user_id}")
    List<UserEntry> isUserExist(@Param("user_id") String user_id);

    @Insert({"insert into user_table(username, password, email, phone_number, user_create_time) " +
            "values('${e.username}', '${e.password}', '${e.email}', '${e.phone_number}', '${e.user_create_time}')"})
    int createNewUser(@Param("e") UserEntry e);

    @Update("update user_table set username=#{e.username}, password=#{e.password}, email=#{e.email}," +
            "phone_number=#{e.phone_number}, avatar=#{e.avatar} where user_id=#{e.user_id}")
    int updateUserInfo(@Param("e") UserEntry e);

    @Select("select * from user_table where user_id = #{user_id}")
    List<UserEntry> getUserInfo(@Param("user_id") String user_id);

    @Select("select * from user_table where username = #{username}")
    List<UserEntry> getUserInfoByUsername(@Param("username") String username);
}