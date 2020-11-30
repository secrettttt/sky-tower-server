package com.skytower.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitRespEventController{

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
        JSONObject result = new JSONObject();
        try{
            result.put("err_no", 0);
            result.put("err_message", "success");
        }
        catch(JSONException e) {
            return e.toString();
        }

        System.out.println(api + " | " + resp + isError + isSuccess);
        System.out.println(result.toString());
        response.addHeader("access-control-allow-origin", "*");
        return result.toString();
    }
}
