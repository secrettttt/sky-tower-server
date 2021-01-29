package com.skytower.controller;

import com.skytower.entry.ProjectEntry;
import com.skytower.entry.UserEntry;
import com.skytower.service.UserService;
import org.json.JSONArray;
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
public class GetUserListController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/user_list", method = RequestMethod.GET)
    public String getUserList (
            @RequestParam(value = "user_id") String user_id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {

            List<UserEntry> userList = userService.getUserInfo(user_id);

            if (userList.size() == 0) {
                respData.put("status", "user does not exist");
            } else {
                List<ProjectEntry> result = userService.getUserList(user_id);

                JSONArray respList = new JSONArray();

                for (int i=0; i<result.size(); i++) {
                    JSONObject respListItem = new JSONObject();
                    respListItem.put("project_id", result.get(i).getProject_id());
                    respListItem.put("project_name", result.get(i).getProject_name());
                    respListItem.put("description", result.get(i).getDescription());
                    respListItem.put("url_online", result.get(i).getUrl_online());
                    respListItem.put("is_monitoring", result.get(i).getIs_monitoring());
                    respListItem.put("create_time", result.get(i).getCreate_time());
                    respListItem.put("user_id", result.get(i).getUser_id());
                    respList.put(respListItem);
                }

                respData.put("user_list", respList);
                respData.put("total", respList.length());
                respData.put("status", "success");
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
