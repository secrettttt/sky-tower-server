package com.skytower.service.impl;

import com.skytower.dao.ProjectMapper;
import com.skytower.entry.ProjectEntry;
import com.skytower.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired(required = false)
    protected ProjectMapper projectMapper;

    @Override
    public int createNewProject(String user_id, String project_name, String description,
                                String url_online, int is_monitoring, long create_time) {
        ProjectEntry e = new ProjectEntry();
        e.setUser_id(user_id);
        e.setProject_name(project_name);
        e.setDescription(description);
        e.setUrl_online(url_online);
        e.setIs_monitoring(is_monitoring);
        e.setCreate_time(create_time);
        return projectMapper.createNewProject(e);
    }
}
