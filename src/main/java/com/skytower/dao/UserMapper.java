package com.skytower.dao;

import com.skytower.entry.UserEntry;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user_table where username = #{e.username} and password = #{e.password}")
    List<UserEntry> checkPermission(@Param("e") UserEntry e);
}
