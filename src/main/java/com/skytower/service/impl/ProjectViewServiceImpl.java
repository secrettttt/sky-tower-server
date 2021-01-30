package com.skytower.service.impl;

import com.skytower.dao.ProjectViewMapper;
import com.skytower.entry.ProjectViewEntry;
import com.skytower.service.ProjectViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectViewServiceImpl implements ProjectViewService {

    @Autowired(required = false)
    protected ProjectViewMapper projectViewMapper;

    @Override
    public int isHasPvUv(String project_id) {
        List<ProjectViewEntry> projectViewEventList = projectViewMapper.getProjectViewEvent(project_id);

        if (projectViewEventList.size() > 0) {
            return 1;
        }
        return 0;
    }
}
