package com.skytower.controller;

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

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

@RestController
public class CreateUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create/new_user", method = RequestMethod.POST)
    public String createNewUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phone_number") String phone_number,
            @RequestParam("user_create_time") long user_create_time,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {
            boolean isUserNameExist = userService.isUserNameExist(username);
            if (isUserNameExist) {
                respData.put("status", "username does exist");
            } else {

                int status = userService.createNewUser(username, password,
                        email, phone_number, user_create_time);

                if (status > 0) {
                    respData.put("status", "success");
                } else {
                    respData.put("status", "createNewUser error");
                }
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}