package com.suqb.satoken.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.suqb.satoken.pojo.TUserEntity;
import com.suqb.satoken.service.TUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author lrkj
 * create time: 2023/4/24
 */
@RequestMapping("/safe")
@RequiredArgsConstructor
@Slf4j
@RestController
public class SafeController {

    private final TUserService userService;
    void safe() {
        // 在当前会话开启二级验证 /s
        StpUtil.openSafe(120);

        // 检测当前会话是否处于二级验证
        StpUtil.isSafe();

        // 检验是否通过二级验证，未通过throw ex
        StpUtil.checkSafe();

        // 获取二级验证剩余有效时间
        StpUtil.getSafeTime();

        // 在当前会话结束二级验证
        StpUtil.closeSafe();
    }
    /**
     * example
     */
    @RequestMapping("/auth/{id}")
    public SaResult deleteBase(@PathVariable Long id) {
        if (!StpUtil.isSafe()) {
            return SaResult.error("删除失败！请先完成二级验证");
        }
        return SaResult.ok("删除成功");
    }

    @RequestMapping("/openSafe")
    public SaResult openSafe(String passwd) {


        TUserEntity user = userService.getById(StpUtil.getLoginIdAsInt());

        if (Objects.equals(user.getPassword(), passwd)) {
            StpUtil.openSafe(120);
            return SaResult.ok("二级验证成功！");
        }
        return SaResult.error("二级验证失败！");
    }
}
