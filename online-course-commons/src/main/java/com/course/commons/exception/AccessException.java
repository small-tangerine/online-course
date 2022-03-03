package com.course.commons.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Gm
 */
@Setter
@Getter
@ToString(callSuper = true)
public class AccessException extends AuthenticationException {
    /**
     * 状态码
     */
    private Integer status = 500;

    public AccessException(String msg, Throwable t) {
        super(msg, t);
    }

    public AccessException(String msg) {
        super(msg);
    }
}