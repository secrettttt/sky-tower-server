package com.skytower.service;

public interface ProjectService {
    int createNewProject(String user_id, String project_name,
    String description, String url_online, int is_monitoring, long create_time);
}
