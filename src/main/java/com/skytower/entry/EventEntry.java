package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventEntry {
    private int eventId;
    private String event;
    private String projectId;
    private String uid;
    private String type;
    private long time;
    private String location;
    private String deviceBrand;
    private String appVersion;
    private String systemVersion;
    private String client;
    private String netType;
    private String ipAddress;
    private String extra;
    private String api;
    private String query;
    private String requestBody;
    private String resp;
    private Boolean isSuccess;
}
