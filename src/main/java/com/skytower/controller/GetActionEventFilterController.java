package com.skytower.controller;

import com.skytower.entry.ActionEventGroupEntry;
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
public class GetActionEventFilterController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/get/filter/action_event", method = RequestMethod.GET)
    public String getActionEventFilter(
            @RequestParam("user_id") String user_id,
            @RequestParam("project_id") String project_id,
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
                List<ActionEventGroupEntry> actionEventFilter = eventService.getActionEventFilter(project_id);

                JSONArray respList = new JSONArray();

                for (int i=0; i<actionEventFilter.size(); i++) {
                    JSONObject respItem = new JSONObject();

                    JSONObject respItemValue = new JSONObject();
                    for (int j=0; j<actionEventFilter.get(i).getValue().size(); j++) {
                        respItemValue.put(actionEventFilter.get(i).getValue().get(j).getLabel(),
                                actionEventFilter.get(i).getValue().get(j).getCount());
                    }

                    respItem.put(actionEventFilter.get(i).getKey(), respItemValue);
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
