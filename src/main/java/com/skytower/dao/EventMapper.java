package com.skytower.dao;

import com.skytower.entry.CountEventGroupEntry;
import com.skytower.entry.EventEntry;
import com.skytower.entry.EventTableEntry;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
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


    @Select("<script>" +
            "select * from event_table " +
            "where type = 'action' and project_id = #{project_id} and time between #{start_time} and #{end_time} " +
            "<if test = 'event.length() != 0'> and event like CONCAT('%', #{event}, '%') </if>" +
            "<if test = 'location.length() != 0'> and location like CONCAT('%', #{location}, '%') </if>" +
            "<if test = 'device_brand.length() != 0'> and device_brand like CONCAT('%', #{device_brand}, '%') </if>" +
            "<if test = 'app_version.length() != 0'> and app_version like CONCAT('%', #{app_version}, '%') </if>" +
            "<if test = 'system_version.length() != 0'> and system_version like CONCAT('%', #{system_version}, '%') </if>" +
            "<if test = 'ip_address.length() != 0'> and ip_address like CONCAT('%', #{ip_address}, '%') </if>" +
//            "<if test = 'client.size() != 0'> and client in (" +
//                "<foreach collection='client' item='client' index='index' separator=',' >" +
//                    "#{client}" +
//                "</foreach>" +
//            ")</if>" +
            "order by time desc " +
            "limit 500 " +
            "</script> ")
    List<EventTableEntry> getActionEventList(@Param("project_id") String project_id,
                                             @Param("start_time") long start_time,
                                             @Param("end_time") long end_time,
                                             @Param("event") String event,
                                             @Param("location") String location,
                                             @Param("device_brand") String device_brand,
                                             @Param("app_version") String app_version,
                                             @Param("system_version") String system_version,
                                             @Param("client") ArrayList<String> client,
                                             @Param("net_type") ArrayList<String> net_type,
                                             @Param("ip_address") String ip_address);

    @Select("select event, COUNT(*) as count from event_table where project_id = #{project_id} and type = 'count' group by event")
    List<CountEventGroupEntry> getAllCountEvent(@Param("project_id") String project_id);

    @Select("select event, COUNT(*) as count from event_table where project_id = #{project_id} " +
            "and type = 'count' and time between #{start_time} and #{end_time} group by event")
    List<CountEventGroupEntry> getCountEventByTime(@Param("project_id") String project_id,
                            @Param("start_time") long start_time, @Param("end_time") long end_time);

    @Select("<script>" +
            "select * from event_table " +
            "where 1=1 " +
            "<if test = 'uid.length() != 0'> and uid = #{uid} </if>" +
            "<if test = 'type.length() != 0'> and type = #{type} </if>" +
            "<if test = 'type.length() == 0'> and (type = 'req' or type = 'resp') </if>" +
            "<if test = 'is_success!=2'> and is_success = #{is_success} </if>" +
            "<if test = 'api.length() != 0'> and api like CONCAT('%', #{api}, '%') </if> " +
            "order by time desc " +
            "limit 300 " +
            "</script> ")
    List<EventTableEntry> getHttpEventList(@Param("uid") String uid,
                                           @Param("api") String api,
                                           @Param("type") String type,
                                           @Param("is_success") int is_success);
}
