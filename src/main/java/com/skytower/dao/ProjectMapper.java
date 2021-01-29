package com.skytower.dao;

import com.skytower.entry.ProjectEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("select * from project_table where user_id = #{user_id} order by create_time desc")
    List<ProjectEntry> getUserList(@Param("user_id") String user_id);

    @Insert({"insert into project_table(user_id, project_name, description, url_online, is_monitoring, create_time) " +
            "values('${e.user_id}', '${e.project_name}', '${e.description}', '${e.url_online}', '${e.is_monitoring}', '${e.create_time}')"})
    int createNewProject(@Param("e") ProjectEntry e);
}
