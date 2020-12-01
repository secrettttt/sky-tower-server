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

}
