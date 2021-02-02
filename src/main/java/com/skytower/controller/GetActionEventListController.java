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
import java.util.ArrayList;
import java.util.List;

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;
import static com.skytower.util.StringUtil.transformArrayStringToArray;

@RestController
public class GetActionEventListController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/get/list/action_event", method = RequestMethod.GET)
    public String getActionEventList(
            @RequestParam("user_id") String user_id,
            @RequestParam("project_id") String project_id,
            @RequestParam("start_time") long start_time,
            @RequestParam("end_time") long end_time,
            @RequestParam(value = "event", required = false, defaultValue = "") String event,
            @RequestParam(value = "location",required = false, defaultValue = "") String location,
            @RequestParam(value = "device_brand", required = false, defaultValue = "") String device_brand,
            @RequestParam(value = "app_version",required = false, defaultValue = "") String app_version,
            @RequestParam(value = "system_version", required = false, defaultValue = "") String system_version,
            @RequestParam(value = "client",required = false, defaultValue = "") String client,
            @RequestParam(value = "net_type", required = false, defaultValue = "") String net_type,
            @RequestParam(value = "ip_address",required = false, defaultValue = "") String ip_address,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        try {

            ArrayList<String> clientArray = transformArrayStringToArray(client);
            ArrayList<String> netTypeArray = transformArrayStringToArray(net_type);

            JSONObject respData = new JSONObject();

            List<ProjectEntry> currentProject = projectService.getProjectInfo(project_id);

            if (currentProject.size() == 0 || (!currentProject.get(0).getUser_id().equals(user_id))) {
                // project does not exist: project本身不存在，或者当前project不属于该user_id的用户
                respData.put("status", "project does not exist");
            } else {
                List<EventTableEntry> actionEventList = eventService.getActionEventList(project_id, start_time, end_time,
                        event, location, device_brand, app_version, system_version, clientArray, netTypeArray, ip_address);

                JSONArray respList = new JSONArray();

                for (int i=0; i<actionEventList.size(); i++) {
                    JSONObject respItem = new JSONObject();
                    respItem.put("event", actionEventList.get(i).getEvent());
                    respItem.put("location", actionEventList.get(i).getLocation());
                    respItem.put("device_brand", actionEventList.get(i).getDevice_brand());
                    respItem.put("app_version", actionEventList.get(i).getApp_version());
                    respItem.put("system_version", actionEventList.get(i).getSystem_version());
                    respItem.put("client", actionEventList.get(i).getClient());
                    respItem.put("net_type", actionEventList.get(i).getNet_type());
                    respItem.put("ip_address", actionEventList.get(i).getIp_address());
                    respItem.put("extra", actionEventList.get(i).getExtra());
                    respItem.put("type", actionEventList.get(i).getType());
                    respItem.put("time", actionEventList.get(i).getTime());
                    respItem.put("project_id", actionEventList.get(i).getProject_id());
                    respItem.put("uid", actionEventList.get(i).getUid());
                    respList.put(respItem);
                }
                respData.put("data", respList);
                respData.put("status", "success");
            }

            checkOriginWhiteList(request, response);
            return respData.toString();
        } catch ( JSONException e) {
            return e.toString();
        }
    }
}
