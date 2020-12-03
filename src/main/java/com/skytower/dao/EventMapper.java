package com.skytower.dao;

import com.skytower.entry.EventEntry;
import org.apache.ibatis.annotations.*;


@Mapper
public interface EventMapper {
//    private int eventId;
//    private String event;
//    private String projectId;
//    private String uid;
//    private String type;
//    private long time;
//    private String location;
//    private String deviceBrand;
//    private String appVersion;
//    private String systemVersion;
//    private String client;
//    private String netType;
//    private String ipAddress;
//    private String extra;
//    private String api;
//    private String query;
//    private String requestBody;
//    private String resp;
//    private String isSuccess;

//    `event_id` INT NOT NULL AUTO_INCREMENT,
//    `event` VARCHAR(25) NOT NULL,
//    `project_id` VARCHAR(25) NOT NULL,
//    `uid` VARCHAR(25) NOT NULL,
//    `type` VARCHAR(25) NOT NULL,
//    `time` BIGINT,
//            `location` VARCHAR(25),
//    `device_brand` VARCHAR(25),
//    `app_version` VARCHAR(25),
//    `system_version` VARCHAR(25),
//    `client` VARCHAR(25),
//    `net_type` VARCHAR(25),
//    `ip_address` VARCHAR(25),
//    `extra` VARCHAR(1000),
//    `api` VARCHAR(100),
//    `query` VARCHAR(100),
//    `request_body` VARCHAR(1000),
//    `resp` VARCHAR(1000),
//    `is_success` VARCHAR(25),
//     PRIMARY KEY (`event_id`)

    @Insert({"insert into event_table(event, type, time, project_id, uid) " +
            "values('${e.event}', '${e.type}', '${e.time}', '${e.projectId}', '${e.uid}')"})
    int createCountEvent(@Param("e") EventEntry e);

    @Insert({"insert into event_table(event, location, device_brand," +
            " app_version, system_version, client, net_type, ip_address," +
            " extra, type, time, project_id, uid) " +
            "values('${e.event}', '${e.location}', '${e.deviceBrand}'," +
            "'${e.appVersion}', '${e.systemVersion}', '${e.client}', '${e.netType}', '${e.ipAddress}'," +
            "'${e.extra}', '${e.type}', '${e.time}', '${e.projectId}', '${e.uid}')"})
    int createActionEvent(@Param("e") EventEntry e);

    @Insert({"insert into event_table(event, api, query, request_body, type, time, project_id, uid) " +
            "values('send_http_req', '${e.api}', '${e.query}', '${e.requestBody}', " +
            "'${e.type}', '${e.time}', '${e.projectId}', '${e.uid}')"})
    int createReqEvent(@Param("e") EventEntry e);

    @Insert({"insert into event_table(event, api, is_success, resp, type, time, project_id, uid) " +
            "values('receive_http_resp', '${e.api}', '${e.isSuccess}', '${e.resp}', " +
            "'${e.type}', '${e.time}', '${e.projectId}', '${e.uid}')"})
    int createRespEvent(@Param("e") EventEntry e);

}
