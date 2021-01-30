package com.skytower.dao;

import com.skytower.entry.EventEntry;
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

    @Select("select * from project_table where project_id = #{project_id}")
    List<ProjectEntry> getProjectInfo(@Param("project_id") String project_id);

    @Insert({"update project_table set project_name = #{e.project_name}, description = #{e.description}," +
            "url_online = #{e.url_online} where project_id = #{e.project_id}"})
    int updateProjectInfo(@Param("e") ProjectEntry e);

    @Insert({"update project_table set is_monitoring = 0 where project_id = #{e.project_id}"})
    int stopMonitoring(String project_id);

    @Select("select * from event_table where project_id = #{project_id} and type = 'action'")
    List<EventEntry> getActionEvent(@Param("project_id") String project_id);

    @Select("select * from event_table where project_id = #{project_id} and type = 'count'")
    List<EventEntry> getCountEvent(@Param("project_id") String project_id);

    @Select("select * from event_table where project_id = #{project_id} and (type = 'req' or type = 'resp')")
    List<EventEntry> getHttpEvent(@Param("project_id") String project_id);
}
