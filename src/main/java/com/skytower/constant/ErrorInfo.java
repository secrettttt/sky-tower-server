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

        errNoMap.put("type is not count", 401);
        errMessageMap.put("type is not count", "error! emit count event but type is not count.");

    }

    public static int getErrNo(String key) {
        return errNoMap.get(key);
    }

    public static String getErrMessage(String key) {
        return errMessageMap.get(key);
    }
}
