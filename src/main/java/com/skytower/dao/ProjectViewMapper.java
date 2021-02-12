package com.skytower.dao;

import com.skytower.entry.ProjectViewEntry;
import com.skytower.entry.PvUvEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectViewMapper {

    @Select("select * from project_view_table where project_id = #{project_id}")
    List<ProjectViewEntry> getProjectViewEvent(@Param("project_id") String project_id);

    @Select("select count(uid) pv, count(DISTINCT (uid)) uv from project_view_table " +
            "where project_id = #{project_id} and view_time between #{start_time} and #{end_time} ")
    List<PvUvEntry> getPvUvByTime(@Param("project_id") String project_id,
                                  @Param("start_time") long start_time,
                                  @Param("end_time") long end_time);

    @Insert({"insert into project_view_table(project_id, uid, view_time) " +
            "values('${project_id}', '${uid}', '${view_time}')"})
    int createRecord(@Param("project_id") String project_id, @Param("uid") String uid,
                     @Param("view_time") long view_time);
}
