package com.suqb.satoken.config;

import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiFunction;

/**
 * @author lrkj
 * create time: 2023/4/24
 */
@Configuration
public class SaTokenConfigure {
    /**
     * 重写Sa-Token内部算法策略
     */
    @Autowired
    public void rewriteSaStrategy() {
        // 重写Token生产策略
        SaStrategy.me.createToken = (loginId, loginType) -> {
            // 生产随机60位字符串
            return SaFoxUtil.getRandomString(60);
        };
    }
}
