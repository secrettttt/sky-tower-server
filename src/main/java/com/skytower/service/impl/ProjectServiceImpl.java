package com.skytower.service.impl;

import com.skytower.dao.ProjectMapper;
import com.skytower.entry.EventEntry;
import com.skytower.entry.ProjectEntry;
import com.skytower.service.EventService;
import com.skytower.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ProjectEntry> getProjectInfo(String project_id) {
        return projectMapper.getProjectInfo(project_id);
    }

    @Override
    public int updateProjectInfo(String project_id, String project_name, String description,
                          String url_online) {
        ProjectEntry e = new ProjectEntry();
        e.setProject_id(Integer.valueOf(project_id));
        e.setProject_name(project_name);
        e.setDescription(description);
        e.setUrl_online(url_online);
        return projectMapper.updateProjectInfo(e);
    }

    @Override
    public int isHasActionEvent(String project_id) {
        List<EventEntry> actionEventList = projectMapper.getActionEvent(project_id);

        if (actionEventList.size() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int isHasCountEvent(String project_id) {
        List<EventEntry> countEventList = projectMapper.getCountEvent(project_id);

        if (countEventList.size() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int isHasHttpEvent(String project_id) {
        List<EventEntry> httpEventList = projectMapper.getHttpEvent(project_id);

        if (httpEventList.size() > 0) {
            return 1;
        }
        return 0;
    }

}
