package com.skytower.dao;

import com.skytower.entry.CountEventGroupEntry;
import com.skytower.entry.EventEntry;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface EventMapper {

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

    @Select("select event, COUNT(*) as count from event_table where project_id = #{project_id} and type = 'count' group by event")
    List<CountEventGroupEntry> getAllCountEvent(@Param("project_id") String project_id);

    @Select("select event, COUNT(*) as count from event_table where project_id = #{project_id} " +
            "and type = 'count' and time between #{start_time} and #{end_time} group by event")
    List<CountEventGroupEntry> getCountEventByTime(@Param("project_id") String project_id,
                            @Param("start_time") long start_time, @Param("end_time") long end_time);
}
