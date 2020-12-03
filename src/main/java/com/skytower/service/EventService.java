package com.skytower.service;

public interface EventService {

    int createCountEvent(String event, String type, long time, String pid, String uid);

    int createActionEvent(String event, String location, String device_brand,
                      String app_version, String system_version, String client, String net_type,
                      String ip_address, String extra, String type, long time, String pid, String uid);

    int createReqEvent(String api, String query, String request_body,
                   String type, long time, String pid, String uid);

    int createRespEvent(String api, Boolean isSuccess, String resp,
            String type, long time, String pid, String uid);
}
