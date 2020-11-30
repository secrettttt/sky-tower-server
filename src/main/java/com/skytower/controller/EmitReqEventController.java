package com.skytower.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitReqEventController{

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
        JSONObject result = new JSONObject();
        try{
            result.put("err_no", 0);
            result.put("err_message", "success");
        }
        catch(JSONException e) {
            return e.toString();
        }

        System.out.println(api + " | " + query);
        System.out.println(result.toString());
        response.addHeader("access-control-allow-origin", "*");
        return result.toString();
    }
}