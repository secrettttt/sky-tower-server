package com.skytower.service.impl;

import com.skytower.dao.ProjectViewMapper;
import com.skytower.entry.ProjectViewEntry;
import com.skytower.entry.PvUvEntry;
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

    @Override
    public List<PvUvEntry> getPvUvByTime(String project_id, long start_time, long end_time) {
        return projectViewMapper.getPvUvByTime(project_id, start_time, end_time);
    }

    @Override
    public int createRecord(String project_id, String uid, long view_time) {
        return projectViewMapper.createRecord(project_id, uid, view_time);
    }
}
