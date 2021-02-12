package com.skytower.controller;

import com.skytower.entry.EventEntry;
import com.skytower.entry.ProjectEntry;
import com.skytower.entry.PvUvEntry;
import com.skytower.service.ProjectService;
import com.skytower.service.ProjectViewService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

@RestController
public class GetPvUvByTimeController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectViewService projectViewService;

    @RequestMapping(value = "/get/by_time/pv_uv", method = RequestMethod.GET)
    public String GetPvUvByTime (
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "project_id") String project_id,
            @RequestParam(value = "start_time", required = false, defaultValue = "") long start_time,
            @RequestParam(value = "end_time", required = false, defaultValue = "") long end_time,
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

                List<PvUvEntry> pvUvByTime = projectViewService.getPvUvByTime(project_id,
                        start_time, end_time);

                respData.put("pv", pvUvByTime.get(0).getPv());
                respData.put("uv", pvUvByTime.get(0).getUv());

                respData.put("status", "success");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
