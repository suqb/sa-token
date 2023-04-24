package com.suqb.satoken.controller;

import cn.dev33.satoken.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lrkj
 * create time: 2023/4/23
 * 注解鉴权
 */
@RestController
@RequestMapping("/anno")
public class AnnoController {

    /**
     * 登录校验
     */
    @SaCheckLogin
    @RequestMapping("/checkLogin")
    public String info() {
        return "已登录用户";
    }

    /**
     * 角色校验
     */
    @SaCheckRole("super.admin")
    @RequestMapping("/addUser")
    public String add() {
        return "user add";
    }

    /**
     * 权限校验
     */
    @SaCheckPermission("user.add")
    @RequestMapping("/addUsers")
    public String save() {
        return "add batch user";
    }

    /**
     * 二级校验
     */
    @SaCheckSafe
    @RequestMapping("/delete")
    public String delete() {
        return "delete success";
    }

    /**
     * base校验
     */
    @SaCheckBasic
    @RequestMapping("/basic")
    public String basic() {
        return "basic success";
    }

    /**
     * 用户封禁校验
     */
    @SaCheckDisable("comment")
    @RequestMapping("/disable")
    public String disable() {
        return "check disable success";
    }

    /**
     * 假设注解位于类，所有方法都必须经过校验，可以用此注解排除
     * @return
     */
    @SaIgnore
    @RequestMapping("/userList")
    public String userList() {
        return "user list";
    }
}
