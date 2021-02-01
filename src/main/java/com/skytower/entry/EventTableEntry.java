package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTableEntry {
    private int event_id;
    private String event;
    private String project_id;
    private String uid;
    private String type;
    private long time;
    private String location;
    private String device_brand;
    private String app_version;
    private String system_version;
    private String client;
    private String net_type;
    private String ip_address;
    private String extra;
    private String api;
    private String query;
    private String request_body;
    private String resp;
    private Boolean is_success;
}