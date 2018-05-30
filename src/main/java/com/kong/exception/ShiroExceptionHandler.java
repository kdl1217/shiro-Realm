package com.kong.exception;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 *
 * @author Kong, created on 2018-05-24T16:10.
 * @since 1.2.0-SNAPSHOT
 */
@ControllerAdvice
public class ShiroExceptionHandler {


    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        return "无访问权限";
    }


    @ResponseBody
    @ExceptionHandler(ExcessiveAttemptsException.class)
    public String handleExcessiveAttemptsException(ExcessiveAttemptsException e) {
        return "登录失败多次，账户锁定10分钟";
    }
}
