package com.skytower.controller;

import com.skytower.entry.EventTableEntry;
import com.skytower.entry.ProjectEntry;
import com.skytower.service.EventService;
import com.skytower.service.ProjectService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

@RestController
public class GetUserAllTypeEventListController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/get/list/user_all_type_event", method = RequestMethod.GET)
    public String GetUserAllTypeEventList (
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "project_id") String project_id,
            @RequestParam(value = "uid", required = false, defaultValue = "") String uid,
            @RequestParam(value = "start_time") long start_time,
            @RequestParam(value = "end_time") long end_time,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {

            List<ProjectEntry> currentProject = projectService.getProjectInfo(project_id);

            if (currentProject.size() == 0 || (!currentProject.get(0).getUser_id().equals(user_id))) {
                // project does not exist: project本身不存在，或者当前project不属于该user_id的用户
                respData.put("status", "project does not exist");
            } else {

                List<EventTableEntry> allTypeEventList = eventService.getUserAllTypeEventList(project_id, uid,
                        start_time, end_time);

                JSONArray respList = new JSONArray();

                for (int i = 0; i < allTypeEventList.size(); i++) {
                    JSONObject respItem = new JSONObject();
                    respItem.put("user_id", allTypeEventList.get(i).getUid());
                    respItem.put("time", allTypeEventList.get(i).getTime());
                    respItem.put("api", allTypeEventList.get(i).getApi());
                    respItem.put("type", allTypeEventList.get(i).getType());
                    respItem.put("event", allTypeEventList.get(i).getEvent());
                    respItem.put("location", allTypeEventList.get(i).getLocation());
                    respItem.put("device_brand", allTypeEventList.get(i).getDevice_brand());
                    respItem.put("app_version", allTypeEventList.get(i).getApp_version());
                    respItem.put("system_version", allTypeEventList.get(i).getSystem_version());
                    respItem.put("client", allTypeEventList.get(i).getClient());
                    respItem.put("net_type", allTypeEventList.get(i).getNet_type());
                    respItem.put("ip_address", allTypeEventList.get(i).getIp_address());
                    respItem.put("is_success", allTypeEventList.get(i).getIs_success());
                    respItem.put("extra", allTypeEventList.get(i).getExtra());
                    respItem.put("query", allTypeEventList.get(i).getQuery());
                    respItem.put("request_body", allTypeEventList.get(i).getRequest_body());
                    respItem.put("resp", allTypeEventList.get(i).getResp());

                    respList.put(respItem);
                }

                respData.put("data", respList);
                respData.put("status", "success");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
