package com.skytower.controller;

import com.skytower.service.EventService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitActionEventController{

    @Autowired
    private EventService eventService;

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

        JSONObject respData = new JSONObject();

        try {

            boolean isCorrectParams = true;

            if (!type.equals("action")) {

                respData.put("status", "type is not action");
                isCorrectParams = false;
            }

            if (event.length() == 0) {

                respData.put("status", "event is undefined");
                isCorrectParams = false;
            }

            if (pid.length() == 0) {

                respData.put("status", "pid is undefined");
                isCorrectParams = false;
            }

            if (isCorrectParams) {

                int status = eventService.createActionEvent(event, location, device_brand,
                        app_version, system_version, client, net_type,
                        ip_address, extra, type, time, pid, uid);

                if (status > 0) {
                    respData.put("status", "success");
                } else {
                    respData.put("status", "createActionEvent error");
                }

            }

        } catch(JSONException e) {
            return e.toString();
        }

        response.addHeader("access-control-allow-origin", "*");
        return respData.toString();
    }
}
