package com.skytower.service.impl;

import com.skytower.dao.EventMapper;
import com.skytower.entry.CountEventGroupEntry;
import com.skytower.entry.EventEntry;
import com.skytower.entry.EventTableEntry;
import com.skytower.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<EventTableEntry> getActionEventList(String project_id, long start_time, long end_time,
                                                        String event, String location, String device_brand,
                                                        String app_version, String system_version, ArrayList<String> client,
                                                        ArrayList<String> net_type, String ip_address) {
        return eventMapper.getActionEventList(project_id, start_time, end_time, event, location, device_brand,
                app_version, system_version, client, net_type, ip_address);
    }

    @Override
    public List<CountEventGroupEntry> getCountEventList(String project_id, long start_time, long end_time) {
        if (start_time == 0 && end_time == 0) {
           return eventMapper.getAllCountEvent(project_id);
        } else {
            return eventMapper.getCountEventByTime(project_id, start_time, end_time);
        }
    }

    @Override
    public List<EventEntry> getHttpEventList(String uid, String api, String type, int is_success) {
       List<EventTableEntry> tempList = eventMapper.getHttpEventList(uid, api, type, is_success);

       List<EventEntry> res = new ArrayList<>();

       for (int i = 0; i < tempList.size(); i++) {
           EventEntry resItem = new EventEntry();
           resItem.setTime(tempList.get(i).getTime());
           resItem.setUid(tempList.get(i).getUid());
           resItem.setApi(tempList.get(i).getApi());
           resItem.setType(tempList.get(i).getType());
           resItem.setIsSuccess(tempList.get(i).getIs_success());
           resItem.setQuery(tempList.get(i).getQuery());
           resItem.setRequestBody(tempList.get(i).getRequest_body());
           resItem.setResp(tempList.get(i).getResp());
           res.add(resItem);
       }
       return res;
    }

    @Override
    public List<EventTableEntry> getUserAllTypeEventList(String project_id, String uid, long start_time,
                                             long end_time) {
        List<EventTableEntry> res = eventMapper.getALLTypeEventList(project_id, uid, start_time, end_time);
        System.out.println(res.size() + " || " + project_id + " || " + uid + " || ");
        return eventMapper.getALLTypeEventList(project_id, uid, start_time, end_time);
    }
}
