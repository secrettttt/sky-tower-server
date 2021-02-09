package com.skytower.controller;

import com.skytower.service.ProjectViewService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitPvUvInfoController {

    @Autowired
    private ProjectViewService projectViewService;

    @RequestMapping(value = "/emit/pv_uv_info", method = RequestMethod.POST)
    public String emitCountEvent(
            @RequestParam("view_time") long view_time,
            @RequestParam("pid") String project_id,
            @RequestParam(value = "uid", required = false, defaultValue = "0") String uid,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {
            int status = projectViewService.createRecord(project_id, uid, view_time);

            if (status > 0) {
                respData.put("status", "success");
            } else {
                respData.put("status", "createRecord error");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        response.addHeader("access-control-allow-origin", "*");
        return respData.toString();
    }
}
