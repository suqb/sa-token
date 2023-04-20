package com.suqb.satoken.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.suqb.satoken.pojo.TUserEntity;
import com.suqb.satoken.service.TUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author suqb 2023/4/20
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    /**
     * 登录
     */
    private final TUserService userService;
    @PostMapping("/login.do")
    public SaResult doLogin(@RequestBody TUserEntity user) {

        log.info("请求登录”{}", user);
        LambdaQueryWrapper<TUserEntity> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper
                .eq(StringUtils.isNotEmpty(user.getUsername()), TUserEntity::getUsername, user.getUsername())
                .eq(StringUtils.isNotEmpty(user.getPassword()), TUserEntity::getPassword, user.getPassword());

        TUserEntity thisUser = userService.getOne(queryWrapper);

        log.info("用户信息：{}", thisUser);

        if (ObjectUtils.isNotEmpty(this.userService)) {
            StpUtil.login(thisUser.getId());
            System.out.println("=====================================");
            System.out.println(StpUtil.getPermissionList());
            return SaResult.ok("登录成功!");
        }

        return SaResult.error("登录失败!");
    }

    /**
     * 查询是否登录
     */
    @RequestMapping("/isLogin.do")
    private SaResult isLogin() {
        if (StpUtil.isLogin()) {
            return SaResult.ok(StpUtil.getTokenInfo().toString());
        }
        return SaResult.error("当前用户未登录!");
    }

    /**
     * 注销登录
     */
    @RequestMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @RequestMapping("/per.do")
    public SaResult per() {
        StpUtil.logout();
        return SaResult.ok();
    }
}
