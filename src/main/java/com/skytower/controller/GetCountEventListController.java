package com.skytower.controller;

import com.skytower.entry.CountEventGroupEntry;
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
public class GetCountEventListController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/get/list/count_event", method = RequestMethod.GET)
    public String getCountEventList (
            @RequestParam("user_id") String user_id,
            @RequestParam("project_id") String project_id,
            @RequestParam(value = "start_time", required = false, defaultValue = "0") long start_time,
            @RequestParam(value = "end_time",required = false, defaultValue = "0") long end_time,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        try {
            JSONObject respData = new JSONObject();

            List<ProjectEntry> currentProject = projectService.getProjectInfo(project_id);

            if (currentProject.size() == 0 || (!currentProject.get(0).getUser_id().equals(user_id))) {
                // project does not exist: project本身不存在，或者当前project不属于该user_id的用户
                respData.put("status", "project does not exist");
            } else {
                List<CountEventGroupEntry> countEventGroupList = eventService.getCountEventList(project_id, start_time, end_time);

                JSONArray respList = new JSONArray();

                for (int i=0; i<countEventGroupList.size(); i++) {
                    JSONObject respItem = new JSONObject();
                    respItem.put(countEventGroupList.get(i).getEvent(),
                            countEventGroupList.get(i).getCount());
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
