package com.course.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**

 * @date 2020-03-12
 * @Description: 不打印的异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class IgnoreException extends RuntimeException {
    private Integer status = 500;
    private String message = "接口错误";
    /**
     * 内部需要展示的错误
     */
    private String errorMessage;

    public IgnoreException(String message) {
        this.message = message;
    }

    public IgnoreException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public IgnoreException(Integer status, String message, String errorMessage) {
        this.status = status;
        this.message = message;
    }

    public IgnoreException(Integer status, String message, Throwable throwable) {
        super(message, throwable);
        this.status = status;
        this.message = message;
    }

    public IgnoreException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public IgnoreException(Integer status, Throwable throwable) {
        super(throwable);
        this.status = status;
        this.message = throwable.getMessage();
    }

    public IgnoreException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }
}
