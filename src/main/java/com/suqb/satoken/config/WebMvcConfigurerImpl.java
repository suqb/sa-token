package com.suqb.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.suqb.satoken.interceptor.PermissionInterceptorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * @author suqb 2023/4/20
 * web MVC 配置类
 * 当使用@SaIgnore注解注解控制器或者方法时，针对SaInterceptor的拦截器和AOP注解将不再生效【自定义拦截器和过滤器依然生效】
 */
//@Configuration
@RequiredArgsConstructor
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    private final PermissionInterceptorImpl permissionInterceptor;

    static {
        System.out.println("开始注册拦截器>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


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

        //  sa-token拦截器 [拦截所有]
        registry
                .addInterceptor(new SaInterceptor(handler -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                        .excludePathPatterns("/login/**");
        //  sa-token拦截器 [按权限拦截]
        registry
                .addInterceptor(new SaInterceptor(handler -> {
                    SaRouter
                            .match("/**")
                            .notMatch("/user/login/**")
                            .check(r -> StpUtil.checkLogin());

                    SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                    SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
                })).addPathPatterns("/**");

        // 更自由的定义拦截规则
        registry
                .addInterceptor(new SaInterceptor(handler -> {
                    // 支持restful风格，并且支持多个同时校验 check函数也可自定义
                    SaRouter.match("/user/**", "/goods/**", "/art/get/{id}").check(r -> StpUtil.checkLogin());

                    // 可以排除匹配
                    SaRouter.match("/**").notMatch("*.html", "*.css", "*.js").check(r -> StpUtil.checkLogin());

                    // 可以根据请求匹配
                    SaRouter.match(SaHttpMethod.GET).check(r -> StpUtil.checkLogin());

                    // 根据boolean值进行匹配
                    SaRouter.match(true).check(r -> StpUtil.checkLogin());

                    // 支持建造者模式
                    SaRouter.match(SaHttpMethod.GET).match("/user/**").check(r -> StpUtil.checkLogin());
                })).addPathPatterns("/**");

        // 支持提前推出匹配链与作用域
        registry
                .addInterceptor(new SaInterceptor(handler -> {
                    // 提前进入控制器或者直接返回内容给前端
                    SaRouter.match("/user/**").check(r -> StpUtil.checkLogin());
                    SaRouter.match("/goods/**").check(r -> StpUtil.checkLogin()).stop();
                    SaRouter.match("/art/get/{id}").check(r -> StpUtil.checkLogin()).back("需要直接返回前端的内容");
                    SaRouter.match("/art/delete/{id}").check(r -> StpUtil.checkLogin());

                    // 打开一个独立的作用域
                    SaRouter
                            .match("/**")
                            .free(r -> {
                                SaRouter.match("/user/**").check(role -> StpUtil.checkLogin());
                                SaRouter.match("/goods/**").check(role -> StpUtil.checkLogin()).stop();
                            });
                    SaRouter.match("/goods/**").check(role -> StpUtil.checkLogin()).stop();
                })).addPathPatterns("/**");

        // 关闭注解校验
        registry
                .addInterceptor(
                        new SaInterceptor(handler -> {
                        SaRouter.match("/user/**").check(r -> StpUtil.checkLogin());

                    }).isAnnotation(false)
                ).addPathPatterns("/**");
    }


}
