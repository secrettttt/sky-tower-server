package com.skytower.interceptor;

import com.skytower.util.JwtUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

public class JwtHandlerInterceptor implements HandlerInterceptor {

    // 在请求处理之前进行调用 (Controller方法调用之前)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        String is_login_in = request.getParameter("is_login_in");

        if (!Boolean.parseBoolean(is_login_in)) {
            // is_login_in为true, 不拦截, 此时checkPermission为登陆接口
            // is_login_in为false, 需要拦截, 此时通过解析token来判断用户的登陆状态
            JSONObject res = JwtUtil.parseToken(token);

            // 如果校验成功返回true
            if(res.getString("status") == "200") {
                return true;
            } else {
                // 校验失败，返回错误信息
                getErrorResponse(request, response);
                return false;
            }
        }

        return true;
    }

    private void getErrorResponse(HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        checkOriginWhiteList(request, response);

        response.setContentType("application/json; charset=utf-8");

        try {

            writer = response.getWriter();

            JSONObject data = new JSONObject();
            data.put("code", "40002");
            data.put("message", "用户令牌token无效");

            writer.print(data);
        } catch (IOException | JSONException e){
            System.out.println(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
