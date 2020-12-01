package com.skytower.service;

public interface EventService {

    int createCountEvent(String event, String type, long time, String pid, String uid);

}
