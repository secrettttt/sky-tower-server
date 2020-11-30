package com.skytower.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitActionEventController{

    @RequestMapping(value = "/emit/action_event", method = RequestMethod.POST)
    public String emitActionEvent(
            @RequestParam(value = "event") String event,
            @RequestParam(value = "location", required = false, defaultValue = "") String location,
            @RequestParam(value = "device_brand", required = false, defaultValue = "") String device_brand,
            @RequestParam(value = "app_version", required = false, defaultValue = "") String app_version,
            @RequestParam(value = "system_version", required = false, defaultValue = "") String system_version,
            @RequestParam(value = "client", required = false, defaultValue = "") String client,
            @RequestParam(value = "net_type", required = false, defaultValue = "") String net_type,
            @RequestParam(value = "ip_address", required = false, defaultValue = "") String ip_address,
            @RequestParam(value = "extra", required = false, defaultValue = "{}") String extra,
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

        System.out.println(event + " | " + extra);
        System.out.println(result.toString());
        response.addHeader("access-control-allow-origin", "*");
        return result.toString();
    }
}
