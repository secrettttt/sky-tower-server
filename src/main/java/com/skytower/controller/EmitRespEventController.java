package com.skytower.controller;

import com.skytower.service.EventService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitRespEventController{

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/emit/resp_event", method = RequestMethod.POST)
    public String emitRespEvent(
            @RequestParam(value = "api") String api,
            @RequestParam(value = "is_success", required = false, defaultValue = "false") Boolean isSuccess,
            @RequestParam(value = "is_error", required = false, defaultValue = "false") Boolean isError,
            @RequestParam(value = "resp", required = false, defaultValue = "{}") String resp,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "time") long time,
            @RequestParam(value = "pid") String pid,
            @RequestParam(value = "uid") String uid,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try{


            boolean isCorrectParams = true;

            if (!type.equals("resp")) {

                respData.put("status", "type is not resp");
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

            if (isCorrectParams && !isSuccess && !isError ) {

                respData.put("status", "isSuccess and isError are false");
                isCorrectParams = false;
            }

            if (isCorrectParams) {

                int status = eventService.createRespEvent(api, isSuccess, resp,
                        type, time, pid, uid);

                if (status > 0) {
                    respData.put("status", "success");
                } else {
                    respData.put("status", "createRespEvent error");
                }

            }
        }
        catch(JSONException e) {
            return e.toString();
        }

        response.addHeader("access-control-allow-origin", "*");
        return respData.toString();
    }
}
