package com.suqb.satoken;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suqb
 */
@SpringBootApplication
public class SaTokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaTokenDemoApplication.class, args);
        System.out.println("启动成功：Sa-token配置如下：" + SaManager.getConfig());
    }

}
