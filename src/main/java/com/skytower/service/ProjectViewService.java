package com.skytower.service;

public interface ProjectViewService {

    int isHasPvUv(String project_id);

    int createRecord(String project_id, String uid, long view_time);
}
