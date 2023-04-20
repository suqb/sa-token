package com.suqb.satoken.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author suqb 2023/4/20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    static {
        log.info("正在初始化全局异常拦截器>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        if (e instanceof NotRoleException || e instanceof NotPermissionException) {
            return SaResult.ok("你无权访问当前信息");
        }
        return SaResult.ok("服务器出错，请联系管理员，电话：1008611");
    }
}
