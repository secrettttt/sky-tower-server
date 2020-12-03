package com.skytower.controller;

import com.skytower.service.EventService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitReqEventController{

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/emit/req_event", method = RequestMethod.POST)
    public String emitReqEvent(
            @RequestParam(value = "api") String api,
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "request_body", required = false, defaultValue = "{}") String request_body,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "time") long time,
            @RequestParam(value = "pid") String pid,
            @RequestParam(value = "uid") String uid,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try{

            boolean isCorrectParams = true;

            if (!type.equals("req")) {

                respData.put("status", "type is not req");
                isCorrectParams = false;
            }

            if (isCorrectParams && api.length() == 0) {

                respData.put("status", "api is undefined");
                isCorrectParams = false;
            }

            if (isCorrectParams && pid.length() == 0) {

                respData.put("status", "pid is undefined");
                isCorrectParams = false;
            }

            if (isCorrectParams) {

                int status = eventService.createReqEvent(api, query, request_body,
                        type, time, pid, uid);

                if (status > 0) {
                    respData.put("status", "success");
                } else {
                    respData.put("status", "createReqEvent error");
                }

            }
        } catch(JSONException e) {
            return e.toString();
        }

        response.addHeader("access-control-allow-origin", "*");
        return respData.toString();
    }
}