package com.skytower.constant;

import java.util.HashMap;
import java.util.Map;

public class ErrorInfo {

    private final static Map<String, Integer> errNoMap = new HashMap<>();
    private final static Map<String, String> errMessageMap = new HashMap();

    static {
        errNoMap.put("success", 0);
        errMessageMap.put("success", "success");

        errNoMap.put("createCountEvent error", 501);
        errMessageMap.put("createCountEvent error", "createCountEvent error");

        errNoMap.put("createActionEvent error", 502);
        errMessageMap.put("createActionEvent error", "createActionEvent error");

        errNoMap.put("createReqEvent error", 503);
        errMessageMap.put("createReqEvent error", "createReqEvent error");

        errNoMap.put("createRespEvent error", 504);
        errMessageMap.put("createRespEvent error", "createRespEvent error");

        errNoMap.put("type is not count", 401);
        errMessageMap.put("type is not count", "error! emit count event but type is not count.");

        errNoMap.put("type is not action", 402);
        errMessageMap.put("type is not action", "error! emit action event but type is not action.");

        errNoMap.put("event is undefined", 403);
        errMessageMap.put("event is undefined", "event is undefined.");

        errNoMap.put("pid is undefined", 405);
        errMessageMap.put("pid is undefined", "pid is undefined.");

        errNoMap.put("type is not req", 406);
        errMessageMap.put("type is not req", "error! emit req event but type is not req.");

        errNoMap.put("api is undefined", 407);
        errMessageMap.put("api is undefined", "api is undefined.");

        errNoMap.put("type is not resp", 408);
        errMessageMap.put("type is not resp", "error! emit resp event but type is not resp.");

        errNoMap.put("isSuccess and isError are false", 409);
        errMessageMap.put("isSuccess and isError are false", " params is_success and is_error are false.");

        errNoMap.put("username or password is empty", 40001);
        errMessageMap.put("username or password is empty", "username or password is empty.");

        errNoMap.put("username or password error", 40001);
        errMessageMap.put("username or password error", "username or password error.");

        errNoMap.put("feedback rate is error", 401);
        errMessageMap.put("feedback rate is error", "feedback rate is error.");

        errNoMap.put("user_id not exist", 402);
        errMessageMap.put("user_id not exist", "user_id not exist.");

        errNoMap.put("createFeedback error", 501);
        errMessageMap.put("createFeedback error", "createFeedback error.");

        errNoMap.put("createNewUser error", 501);
        errMessageMap.put("createNewUser error", "createNewUser error.");

        errNoMap.put("updateUserInfo error", 501);
        errMessageMap.put("updateUserInfo error", "updateUserInfo error.");

        errNoMap.put("user does not exist", 404);
        errMessageMap.put("user does not exist", "user does not exist.");

        errNoMap.put("username does not exist", 405);
        errMessageMap.put("username does not exist", "username does not exist.");

        errNoMap.put("createNewProject error", 501);
        errMessageMap.put("createNewProject error", "createNewProject error.");

        errNoMap.put("invalid argument", 401);
        errMessageMap.put("invalid argument", "invalid argument.");

        errNoMap.put("project does not exist", 401);
        errMessageMap.put("project does not exist", "project does not exist.");

        errNoMap.put("updateProjectInfo error", 501);
        errMessageMap.put("updateProjectInfo error", "updateProjectInfo error.");

        errNoMap.put("get event list error", 501);
        errMessageMap.put("get event list error", "get event list error.");

        errNoMap.put("createRecord error", 401);
        errMessageMap.put("createRecord error", "createRecord error.");

        errNoMap.put("file is empty", 400);
        errMessageMap.put("file is empty", "file is empty.");
    }

    public static int getErrNo(String key) {
        return errNoMap.get(key);
    }

    public static String getErrMessage(String key) {
        return errMessageMap.get(key);
    }
}
