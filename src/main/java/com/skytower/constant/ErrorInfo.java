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

        errNoMap.put("type is not count", 401);
        errMessageMap.put("type is not count", "error! emit count event but type is not count.");

        errNoMap.put("type is not action", 402);
        errMessageMap.put("type is not action", "error! emit action event but type is not action.");

        errNoMap.put("event is undefined", 403);
        errMessageMap.put("event is undefined", "event is undefined.");

        errNoMap.put("pid is undefined", 405);
        errMessageMap.put("pid is undefined", "pid is undefined.");

    }

    public static int getErrNo(String key) {
        return errNoMap.get(key);
    }

    public static String getErrMessage(String key) {
        return errMessageMap.get(key);
    }
}
