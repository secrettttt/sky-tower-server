package com.skytower.controller;

import com.skytower.entry.ProjectEntry;
import com.skytower.entry.UserEntry;
import com.skytower.service.ProjectService;
import com.skytower.service.ProjectViewService;
import com.skytower.service.UserService;
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
public class GetProjectDetailController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectViewService projectViewService;

    @RequestMapping(value = "/get/project_detail", method = RequestMethod.GET)
    public String getUserInfo (
            @RequestParam(value = "project_id") String project_id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {

            List<ProjectEntry> projectInfo = projectService.getProjectInfo(project_id);

            if (projectInfo.size() != 0) {
                respData.put("project_id", projectInfo.get(0).getProject_id());
                respData.put("project_name", projectInfo.get(0).getProject_name());
                respData.put("description", projectInfo.get(0).getDescription());
                respData.put("url_online", projectInfo.get(0).getUrl_online());
                respData.put("create_time", projectInfo.get(0).getCreate_time());
                respData.put("is_monitoring", projectInfo.get(0).getIs_monitoring());
                respData.put("user_id", projectInfo.get(0).getUser_id());

                List<UserEntry> projectOwner = userService.getUserInfo("" + projectInfo.get(0).getUser_id());
                if (projectOwner.size() != 0) {
                    respData.put("user_id", projectOwner.get(0).getUser_id());
                    respData.put("username", projectOwner.get(0).getUsername());
                    respData.put("user_create_time", projectOwner.get(0).getUser_create_time());
                    respData.put("avatar", projectOwner.get(0).getAvatar());
                }

                respData.put("is_has_pv_uv", projectViewService.isHasPvUv(project_id));

                respData.put("is_has_action_event", projectService.isHasActionEvent(project_id));
                respData.put("is_has_count_event", projectService.isHasCountEvent(project_id));
                respData.put("is_has_http_event", projectService.isHasHttpEvent(project_id));

                respData.put("status", "success");
            } else {
                respData.put("status", "project does not exist");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
