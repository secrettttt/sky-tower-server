package com.skytower.controller;

import com.skytower.entry.ProjectEntry;
import com.skytower.service.ProjectService;
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
public class UpdateProjectInfoController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/update/project_info", method = RequestMethod.POST)
    public String updateUserInfo(
            @RequestParam("user_id") String user_id,
            @RequestParam("project_id") String project_id,
            @RequestParam("project_name") String project_name,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam("url_online") String url_online,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        JSONObject respData = new JSONObject();

        try {

            if (project_name.equals("") || url_online.equals("")) {
                respData.put("status", "invalid argument");
            } else {
                List<ProjectEntry> currentProject = projectService.getProjectInfo(project_id);

                if (currentProject.size() == 0 || (!currentProject.get(0).getUser_id().equals(user_id))) {
                    // project does not exist: project本身不存在，或者当前project不属于该user_id的用户
                    respData.put("status", "project does not exist");
                } else {
                    int status = projectService.updateProjectInfo(project_id, project_name, description,
                            url_online);

                    if (status > 0) {
                        respData.put("status", "success");
                    } else {
                        respData.put("status", "updateProjectInfo error");
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
