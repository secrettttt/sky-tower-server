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
import static com.skytower.util.JwtUtil.generateToken;

@RestController
public class CheckPermissionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check_permission", method = RequestMethod.POST)
    public String checkPermission(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            @RequestParam(value = "password", required = false, defaultValue = "") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {

            if (username.length() == 0 && password.length() == 0) {
                // 拦截器未拦截请求，checkPermission接口返回success
                respData.put("status", "success");
            } else {
                // 用户登陆，需要判断用户填写的用户名和密码是否正确
                List<UserEntry> result = userService.checkPermission(username, password);

                if (result.size() != 0) {
                    if (result.get(0).getUser_id().equals("")) {
                        respData.put("status", "username or password is empty");
                    } else {
                        respData.put("token", generateToken(result.get(0).getUsername()));
                        respData.put("status", "success");
                    }
                } else {
                    respData.put("status", "username or password error");
                }
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
