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
public class UpdateUserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update/user_info", method = RequestMethod.POST)
    public String updateUserInfo(
            @RequestParam("user_id") String user_id,
            @RequestParam("avatar") String avatar,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phone_number") String phone_number,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        JSONObject respData = new JSONObject();

        try {
            List<UserEntry> result = userService.getUserInfo(user_id);

            if (!username.equals(result.get(0).getUsername()) && userService.isUserNameExist(username)) {
                // 用户修改了用户名，并且修改后的用户名在user_table中已存在

                respData.put("status", "username does not exist");
            } else {
                int status = userService.updateUserInfo(user_id, avatar, username, password,
                        email, phone_number);

                if (status > 0) {
                    respData.put("status", "success");
                } else {
                    respData.put("status", "updateUserInfo error");
                }
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
