package com.suqb.satoken.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suqb 2023/4/20
 * 注销或下线用户
 */
@RestController
@RequestMapping("/forceUserLeave")
public class ForceUserLogout {

    @GetMapping("/forceUserLogoffById")
    public SaResult forceUserLogoffById(Long id) {
        StpUtil.logout(id);
        return SaResult.ok(String.format("成功注销%s用户", id));
    }

    @GetMapping("/forceUserLogoffByIdAndDevice")
    public SaResult forceUserLogoffByIdAndDevice(Long id, String device) {
        StpUtil.logout(id, device);
        return SaResult.ok(String.format("成功注销%s端用户%s", device, id));
    }

    @GetMapping("/forceUserLogoutById")
    public SaResult forceUserLogoutByIdI(Long id) {
        StpUtil.kickout(id);
        return SaResult.ok(String.format("成功下线%s用户", id));
    }

    @GetMapping("/forceUserLogoutByIdAndDevice")
    public SaResult forceUserLogoutByIdAndDevice(Long id, String device) {
        StpUtil.kickout(id, device);
        return SaResult.ok(String.format("成功下线%s端用户%s", device, id));
    }
}
