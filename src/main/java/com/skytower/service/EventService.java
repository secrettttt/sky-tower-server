package com.skytower.service;

import com.skytower.entry.CountEventGroupEntry;
import com.skytower.entry.EventEntry;
import com.skytower.entry.EventTableEntry;

import java.util.ArrayList;
import java.util.List;

public interface EventService {

    int createCountEvent(String event, String type, long time, String pid, String uid);

    int createActionEvent(String event, String location, String device_brand,
                      String app_version, String system_version, String client, String net_type,
                      String ip_address, String extra, String type, long time, String pid, String uid);

    int createReqEvent(String api, String query, String request_body,
                   String type, long time, String pid, String uid);

    int createRespEvent(String api, Boolean isSuccess, String resp,
            String type, long time, String pid, String uid);

    List<EventTableEntry> getActionEventList(String project_id, long start_time, long end_time,
                                             String event, String location, String device_brand,
                                             String app_version, String  system_version, ArrayList<String> client,
                                             ArrayList<String> net_type, String ip_address);

    List<CountEventGroupEntry> getCountEventList(String project_id, long start_time, long end_time);

    List<EventEntry> getHttpEventList(String uid, String api, String type, int is_success);

    List<EventTableEntry> getUserAllTypeEventList(String project_id, String uid, long start_time,
                                             long end_time);
}
