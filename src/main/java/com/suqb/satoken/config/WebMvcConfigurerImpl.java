package com.suqb.satoken.config;

import com.suqb.satoken.interceptor.PermissionInterceptorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * @author suqb 2023/4/20
 * web MVC 配置类
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
    private final PermissionInterceptorImpl permissionInterceptor;

    /**
     * 配置权限拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        ArrayList<String> excludePathPatternList = new ArrayList<>();

        excludePathPatternList.add("/auth/login.do");
        excludePathPatternList.add("/auth/isLogin.do");

        registry
                .addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatternList);
    }
}
