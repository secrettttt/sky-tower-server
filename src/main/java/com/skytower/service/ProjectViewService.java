package com.skytower.service;

import com.skytower.entry.PvUvEntry;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface ProjectViewService {

    int isHasPvUv(String project_id);

    int createRecord(String project_id, String uid, long view_time);

    List<PvUvEntry> getPvUvByTime(String project_id, long start_time, long end_time);

    JSONArray getPvUvInfo(String project_id, long currentDime, int count, long time) throws JSONException;
}
