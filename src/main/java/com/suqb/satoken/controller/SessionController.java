package com.suqb.satoken.controller;

import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lrkj
 * create time: 2023/4/24
 */
@RequiredArgsConstructor
@RequestMapping("/session")
@RestController
public class SessionController {

    @RequestMapping("session")
    public String session() {

        // 获取当前账号session[登录可用]
        StpUtil.getSession();

        // 获取当前账号id的session [当前session不存在是是否创建返回]
        StpUtil.getSession(true);

        StpUtil.getSessionByLoginId(100);

        StpUtil.getSessionByLoginId(100, true);

        StpUtil.getSessionBySessionId("www-www");

        return null;
    }

    @RequestMapping("token-session")
    public String tokenSession() {

        StpUtil.getTokenSession();

        StpUtil.getTokenSessionByToken("token");

        return null;
    }

    @RequestMapping("definition-session")
    public String definitionSession() {

        SaSessionCustomUtil.isExists("goods-1001");

        SaSessionCustomUtil.getSessionById("goods-1001");

        SaSessionCustomUtil.getSessionById("goods-1001", true);

        SaSessionCustomUtil.deleteSessionById("goods-1001");


        return null;
    }
}
