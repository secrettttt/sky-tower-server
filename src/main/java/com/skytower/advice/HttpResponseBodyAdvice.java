package com.skytower.advice;

import com.skytower.constant.ErrorInfo;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class HttpResponseBodyAdvice implements ResponseBodyAdvice<String> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 返回true，表示启动拦截
        return true;
    }

    @Override
    public String beforeBodyWrite(String body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        JSONObject resp = new JSONObject();
        try {
            JSONObject data = new JSONObject(body);

            String status = data.get("status").toString();
            resp.put("err_no", ErrorInfo.getErrNo(status));
            resp.put("err_message", ErrorInfo.getErrMessage(status));

            data.remove("status");
            resp.put("data", data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resp.toString();
    }

}
