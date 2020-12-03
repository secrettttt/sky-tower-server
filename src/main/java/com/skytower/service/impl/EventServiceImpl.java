package com.skytower.service.impl;

import com.skytower.dao.EventMapper;
import com.skytower.entry.EventEntry;
import com.skytower.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired(required = false)
    protected EventMapper eventMapper;

    @Override
    public int createCountEvent(String event, String type, long time, String pid, String uid) {
        EventEntry e = new EventEntry();
        e.setEvent(event);
        e.setType(type);
        e.setTime(time);
        e.setProjectId(pid);
        e.setUid(uid);
        return eventMapper.createCountEvent(e);
    }

    @Override
    public int createActionEvent(String event, String location, String device_brand,
                          String app_version, String system_version, String client, String net_type,
                          String ip_address, String extra, String type, long time, String pid, String uid) {
        EventEntry e = new EventEntry();
        e.setEvent(event);
        e.setLocation(location);
        e.setDeviceBrand(device_brand);
        e.setAppVersion(app_version);
        e.setSystemVersion(system_version);
        e.setClient(client);
        e.setNetType(net_type);
        e.setIpAddress(ip_address);
        e.setExtra(extra);
        e.setType(type);
        e.setTime(time);
        e.setProjectId(pid);
        e.setUid(uid);
        return eventMapper.createActionEvent(e);
    }

    @Override
    public int createReqEvent(String api, String query, String request_body,
                       String type, long time, String pid, String uid) {

        EventEntry e = new EventEntry();
        e.setApi(api);
        e.setQuery(query);
        e.setRequestBody(request_body);
        e.setType(type);
        e.setTime(time);
        e.setProjectId(pid);
        e.setUid(uid);
        return eventMapper.createReqEvent(e);
    }

    @Override
    public int createRespEvent(String api, Boolean isSuccess, String resp,
                               String type, long time, String pid, String uid) {

        EventEntry e = new EventEntry();
        e.setApi(api);
        e.setIsSuccess(isSuccess);
        e.setResp(resp);
        e.setType(type);
        e.setTime(time);
        e.setProjectId(pid);
        e.setUid(uid);
        return eventMapper.createRespEvent(e);
    }

}
