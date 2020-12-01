package com.skytower.dao;

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
            "values('${event}', '${type}', '${time}', '${pid}', '${uid}')"})
    int createCountEvent(String event, String type, long time, String pid, String uid);

}
