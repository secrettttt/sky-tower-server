package com.skytower.controller;

import com.skytower.dao.EventMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmitCountEventController{

    @Autowired
    private EventMapper eventMapper;

    @RequestMapping(value = "/emit/count_event", method = RequestMethod.POST)
    public String emitCountEvent(
            @RequestParam("event") String event,
            @RequestParam("type") String type,
            @RequestParam("time") long time,
            @RequestParam("pid") String pid,
            @RequestParam("uid") String uid,
            HttpServletResponse response
    ) {
        int status = eventMapper.createCountEvent(event, type, time, pid, uid);

        JSONObject result = new JSONObject();
        try {
            if (status > 0) {
                result.put("err_no", 0);
                result.put("err_message", "success");
            } else {
                result.put("err_no", 1);
                result.put("err_message", "error");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        response.addHeader("access-control-allow-origin", "*");
        return result.toString();
    }
}

// post请求接口demo 支持白名单 跨域访问
//@RestController
//public class EmitCountEventController{
//
//    @CrossOrigin(origins = {"http://localhost:8889", "http://0.0.0.0:8889"}, allowCredentials = "true")
//    @RequestMapping(value = "/emit/count_event", method = RequestMethod.POST)
//    public String emitCountEvent(
//            @RequestParam("event") String event,
//            @RequestParam("type") String type,
//            @RequestParam("time") long time,
//            @RequestParam("pid") String pid,
//            @RequestParam("uid") String uid
//    ) {
//        JSONObject result = new JSONObject();
//        try{
//            result.put("err_no", 0);
//            result.put("err_message", "success");
//            result.put("data", "[]");
//        }
//        catch(JSONException e){
//            return e.toString();
//        }
//        System.out.println(event + type + time + pid + uid);
//        System.out.println(result.toString());
//
//        return result.toString();
//    }
//}

// get请求demo 先不删了
//    @RequestMapping(value = "/emit/count_event", method = RequestMethod.GET)
//    public String emitCountEvent(
//            @RequestParam("event") String event,
//            @RequestParam("type") String type,
//            @RequestParam("time") long time,
//            @RequestParam("pid") String pid,
//            @RequestParam("uid") String uid
//    ) {
//
//        JSONObject result = new JSONObject();
//        try{
//            result.put("err_no", 0);
//            result.put("err_message", "success");
//        }
//        catch(JSONException e){
//            return e.toString();
//        }
//        System.out.println(result.toString());
//        return result.toString();
//    }
//}