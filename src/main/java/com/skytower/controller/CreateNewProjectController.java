package com.skytower.controller;

import com.skytower.entry.UserEntry;
import com.skytower.service.ProjectService;
import com.skytower.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

@RestController
public class CreateNewProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create/new_project", method = RequestMethod.POST)
    public String createNewProject(
            @RequestParam("user_id") String user_id,
            @RequestParam("project_name") String project_name,
            @RequestParam("description") String description,
            @RequestParam("url_online") String url_online,
            @RequestParam("create_time") long create_time,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        JSONObject respData = new JSONObject();
        final int initMonitoringStatus = 1;

        try {
            List<UserEntry> currentUser = userService.getUserInfo(user_id);
            if (currentUser.size() == 0) {
                respData.put("status", "user does not exist");
            } else {
                if (project_name.equals("") || url_online.equals("") || create_time == 0) {
                    // 判断create_time是否为空：long、int如果没有赋值，默认为0
                    respData.put("status", "invalid argument");
                } else {
                    int status = projectService.createNewProject(user_id, project_name,
                            description, url_online, initMonitoringStatus, create_time);

                    if (status > 0) {
                        respData.put("status", "success");
                    } else {
                        respData.put("status", "createNewProject error");
                    }
                }
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
