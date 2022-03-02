package com.course.commons.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @date 2020-03-27
 * @Description: 验证用户名密码异常
 */
public class MyAuthenticationException extends AuthenticationException {
    public MyAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyAuthenticationException(String msg) {
        super(msg);
    }
}
