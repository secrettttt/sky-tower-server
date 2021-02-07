package com.skytower.controller;

import com.skytower.entry.AjaxErrorRateListEntry;
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
public class GetAjaxErrorRateController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/get/rate/ajax_error", method = RequestMethod.GET)
    public String getAjaxErrorRate (
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
                List<AjaxErrorRateListEntry> ajaxErrorRateList = eventService.getAjaxErrorList(project_id, start_time, end_time);

                JSONArray respList = new JSONArray();

                for (int i=0; i<ajaxErrorRateList.size(); i++) {
                    JSONObject respItem = new JSONObject();

                    JSONObject respItemValue = new JSONObject();

                    final int currentSuccessCount = ajaxErrorRateList.get(i).getSuccess_count();
                    final int currentErrorCount = ajaxErrorRateList.get(i).getError_count();

                    respItemValue.put("success_count", currentSuccessCount);
                    respItemValue.put("error_count", currentErrorCount);
                    respItemValue.put("ajax_error_rate", (
                            (double) currentErrorCount / (currentSuccessCount + currentErrorCount)
                    ));

                    respItem.put(ajaxErrorRateList.get(i).getApi(), respItemValue);

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
