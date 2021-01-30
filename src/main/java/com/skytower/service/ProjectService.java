package com.skytower.service;

import com.skytower.entry.ProjectEntry;

import java.util.List;

public interface ProjectService {
    int createNewProject(String user_id, String project_name,
    String description, String url_online, int is_monitoring, long create_time);

    List<ProjectEntry> getProjectInfo(String project_id);

    int isHasActionEvent(String project_id);

    int isHasCountEvent(String project_id);

    int isHasHttpEvent(String project_id);
}
