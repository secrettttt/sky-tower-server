package com.skytower.controller;

import com.skytower.entry.ProjectEntry;
import com.skytower.entry.PvUvEntry;
import com.skytower.service.ProjectService;
import com.skytower.service.ProjectViewService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

@RestController
public class GetPvUvInfoController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectViewService projectViewService;

    @RequestMapping(value = "/get/info/pv_uv", method = RequestMethod.GET)
    public String GetPvUvInfo (
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "project_id") String project_id,
            @RequestParam(value = "type") String type,
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
                long currentDime = new Date().getTime();

                List<PvUvEntry> pvUvByTime = projectViewService.getPvUvByTime(project_id,
                        0, currentDime);

                if (type.equals("last day")) {
                    JSONArray data = projectViewService.getPvUvInfo(project_id,
                            currentDime, 12, 60 * 60 * 2 * 1000L);
                    respData.put("data", data);
                } else if (type.equals("last week")) {
                    JSONArray data = projectViewService.getPvUvInfo(project_id,
                            currentDime, 7, 60 * 60 * 24 * 1000L);
                    respData.put("data", data);
                } else if (type.equals("last month")) {
                    JSONArray data = projectViewService.getPvUvInfo(project_id,
                            currentDime, 4, 60 * 60 * 24 * 7 * 1000L);
                    respData.put("data", data);
                } else if (type.equals("last six month")) {
                    JSONArray data = projectViewService.getPvUvInfo(project_id,
                            currentDime,  6, 60 * 60 * 24 * 30 * 1000L);
                    respData.put("data", data);
                }

                respData.put("all_pv", pvUvByTime.get(0).getPv());
                respData.put("all_uv", pvUvByTime.get(0).getUv());
                respData.put("status", "success");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
