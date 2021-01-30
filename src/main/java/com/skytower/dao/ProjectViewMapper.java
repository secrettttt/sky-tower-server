package com.skytower.dao;

import com.skytower.entry.ProjectViewEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectViewMapper {

    @Select("select * from project_view_table where project_id = #{project_id}")
    List<ProjectViewEntry> getProjectViewEvent(@Param("project_id") String project_id);
}
