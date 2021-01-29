package com.skytower.controller;

import com.skytower.entry.UserEntry;
import com.skytower.service.UserService;
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
public class GetUserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/user_info", method = RequestMethod.GET)
    public String getUserInfo (
            @RequestParam(value = "user_id") String user_id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {

            List<UserEntry> result = userService.getUserInfo(user_id);

            if (result.size() != 0) {
                respData.put("user_id", result.get(0).getUser_id());
                respData.put("username", result.get(0).getUsername());
                respData.put("email", result.get(0).getEmail());
                respData.put("phone_number", result.get(0).getPhone_number());
                respData.put("avatar", result.get(0).getAvatar());
                respData.put("status", "success");
            } else {
                respData.put("status", "user does not exist");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
