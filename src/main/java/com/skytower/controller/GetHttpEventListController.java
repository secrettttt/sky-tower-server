package com.skytower.controller;

import com.skytower.entry.EventEntry;
import com.skytower.entry.ProjectEntry;
import com.skytower.entry.UserEntry;
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
public class GetHttpEventListController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/get/list/http_event", method = RequestMethod.GET)
    public String GetHttpEventList (
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "project_id") String project_id,
            @RequestParam(value = "uid", required = false, defaultValue = "") String uid,
            @RequestParam(value = "api", required = false, defaultValue = "") String api,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "is_success", required = false, defaultValue = "2") int is_success,
            // defaultValue = 2：约定is_success = 2 表示既包含成功又包含失败请求
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

                List<EventEntry> httpEventList = eventService.getHttpEventList(uid,
                        api, type, is_success);

                JSONArray respList = new JSONArray();
                for (int i = 0; i < httpEventList.size(); i++) {
                    JSONObject respItem = new JSONObject();
                    respItem.put("time", httpEventList.get(i).getTime());
                    respItem.put("user_id", httpEventList.get(i).getUid());
                    respItem.put("api", httpEventList.get(i).getApi());
                    respItem.put("type", httpEventList.get(i).getType());
                    respItem.put("is_success", httpEventList.get(i).getIsSuccess());
                    respItem.put("query", httpEventList.get(i).getQuery());
                    respItem.put("request_body", httpEventList.get(i).getRequestBody());
                    respItem.put("resp", httpEventList.get(i).getResp());
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
