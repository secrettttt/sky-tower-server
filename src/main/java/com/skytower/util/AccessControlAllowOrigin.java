package com.skytower.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AccessControlAllowOrigin {
    // 跨域请求的白名单
    private static String[] allowDomains = {
            "http://101.200.197.197",
            "http://localhost:9998"
    };

    public static void checkOriginWhiteList(HttpServletRequest request, HttpServletResponse response) {
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeads = request.getHeader("Origin");
        if (allowOrigins.contains(originHeads)) {
            // 设置允许跨域的配置
            response.setHeader("Access-Control-Allow-Origin", originHeads);
        }
    }
}

