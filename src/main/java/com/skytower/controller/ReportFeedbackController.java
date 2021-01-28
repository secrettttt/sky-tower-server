package com.skytower.controller;

import com.skytower.service.FeedbackService;
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
import static com.skytower.util.StringUtil.isCanTransformToNumber;

@RestController
public class ReportFeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/report_feedback", method = RequestMethod.POST)
    public String reportFeedback(
            @RequestParam(value = "user_id", required = false, defaultValue = "0") String user_id,
            @RequestParam(value = "feedback_rate") String feedback_rate,
            @RequestParam(value = "report_content", required = false, defaultValue = "") String report_content,
            @RequestParam(value = "create_time") long create_time,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        try {

            if (!isCanTransformToNumber(feedback_rate)
                    || Integer.parseInt(feedback_rate) < 0
                    || Integer.parseInt(feedback_rate) > 5 ) {
                respData.put("status", "feedback rate is error");
            } else {
                int insertStatus = feedbackService.reportFeedback(user_id,
                        feedback_rate, report_content, create_time);

                int userIdStatus = 1;

                if (!user_id.equals("0") && !userService.isUserExist(user_id)) {
                    respData.put("status", "user_id not exist");
                } else {
                    if (insertStatus > 0 && userIdStatus > 0) {
                        respData.put("status", "success");
                    } else {
                        respData.put("status", "createFeedback error");
                    }
                }
            }
        } catch (JSONException e) {
            return e.toString();
        }

        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}
