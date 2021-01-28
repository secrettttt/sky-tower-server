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
                // 排除不需要token校验的接口，path一定要完全匹配，否则会被拦截
                .excludePathPatterns("/report_feedback")
                .excludePathPatterns("/emit/action_event")
                .excludePathPatterns("/emit/count_event")
                .excludePathPatterns("/emit/req_event")
                .excludePathPatterns("/emit/resp_event");
    }
}
