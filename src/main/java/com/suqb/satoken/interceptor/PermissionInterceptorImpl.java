package com.suqb.satoken.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suqb 2023/4/20
 */
@Slf4j
@Component
public class PermissionInterceptorImpl implements HandlerInterceptor {

    /**
     * 权限拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取当前账号的所有权限集合
        StpUtil.getPermissionList();

        // 判断账号是否拥有指定权限集合
        StpUtil.hasPermission("user.add");

        // 校验当前用户是否拥有指定权限，没有则throw NotPermissionException
        StpUtil.checkPermission("user.add");

        // 校验当前账号是否拥有指定权限 [需全部拥有]
        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.update");

        // 校验当前账号是否拥有指定权限 [拥有其中一个即可]
        StpUtil.checkPermissionOr("user.add", "user.update");

        //============================================================================================================//

        // 获取当前账号的角色集合
        StpUtil.getRoleList();

        // 判断当前账户是否拥有指定角色
        StpUtil.hasRole("admin");

        // 校验当前用户是否拥有指定角色，没有则throw NotRoleException
        StpUtil.checkRole("admin");

        // 校验当前账号是否拥有指定角色 [需全部拥有]
        StpUtil.checkRoleAnd("admin", "super-admin");

        // 校验当前账号是否拥有指定角色 [拥有其中一个即可]
        StpUtil.checkRoleOr("admin", "super-admin");

        return true;
    }
}































