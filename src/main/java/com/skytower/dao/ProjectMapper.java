package com.skytower.dao;

import com.skytower.entry.ProjectEntry;
import com.skytower.entry.UserEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("select * from project_table where user_id = #{user_id} order by create_time desc")
    List<ProjectEntry> getUserList(@Param("user_id") String user_id);
}
