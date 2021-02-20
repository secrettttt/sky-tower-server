package com.skytower.service.impl;

import com.skytower.dao.ProjectViewMapper;
import com.skytower.entry.ProjectViewEntry;
import com.skytower.entry.PvUvEntry;
import com.skytower.service.ProjectViewService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @Override
    public JSONArray getPvUvInfo(String project_id, long currentDime, int count,
                                 long time) throws JSONException {
        JSONArray resList = new JSONArray();
        int countIndex = count - 1;
        while (countIndex >= 0) {
            List<PvUvEntry> pvUvInfo = projectViewMapper.getPvUvByTime(project_id,
                    (long)currentDime - ((countIndex + 1) * time),
                    (long)currentDime - (countIndex * time));
            JSONObject currentPvInfo = new JSONObject();
            JSONObject currentUvInfo = new JSONObject();

            long currentTime = currentDime - (countIndex + 1) * time;
            String currentTimeString = String.valueOf(currentTime);

            currentPvInfo.put("date", currentTimeString);
            currentPvInfo.put("key", "pv");
            currentPvInfo.put("value", pvUvInfo.get(0).getPv());

            currentUvInfo.put("date", currentTimeString);
            currentUvInfo.put("key", "uv");
            currentUvInfo.put("value", pvUvInfo.get(0).getUv());

            resList.put(currentPvInfo);
            resList.put(currentUvInfo);
            countIndex--;
        }
        return resList;
    }
}
