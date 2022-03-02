package com.course.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**

 * @date 2019-12-17
 * @Description: 接口调用异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class ResultException extends RuntimeException {
    private Integer status = 500;
    private String message = "接口错误";
    private String errorMessage;

    public ResultException(String message) {
        this.message = message;
    }

    public ResultException(String message, String errorMessage) {
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public ResultException(String message, String errorMessage, Throwable throwable) {
        super(message, throwable);
        this.errorMessage = errorMessage;

    }

    public ResultException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultException(Integer status, String message, Throwable throwable) {
        super(message, throwable);
        this.status = status;
        this.message = message;
    }

    public ResultException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public ResultException(Integer status, Throwable throwable) {
        super(throwable);
        this.status = status;
        this.message = throwable.getMessage();
    }

    public ResultException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }
}
