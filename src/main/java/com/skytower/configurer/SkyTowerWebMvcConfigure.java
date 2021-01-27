package com.skytower.configurer;

import com.skytower.interceptor.JwtHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SkyTowerWebMvcConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtHandlerInterceptor())
                .addPathPatterns("/**")
                // 排除不需要token校验的接口
                .excludePathPatterns(
                    "/emit/action_event",
                    "/emit/count_event",
                    "/emit/req_event",
                    "/emit/resp_event"
                );
    }
}
