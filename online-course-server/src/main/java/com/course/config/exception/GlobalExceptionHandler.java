package com.course.config.exception;

import com.course.commons.enums.ResultCodeEnum;
import com.course.commons.exception.AccessException;
import com.course.commons.exception.IgnoreException;
import com.course.commons.exception.ResultException;
import com.course.commons.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author Gm
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 调用第三方接口异常
     *
     * @param e 异常
     * @return response
     */
    @ExceptionHandler(value = ResultException.class)
    public Response handleResultException(ResultException e) {
        return Response.fail(e.getMessage());
    }

    /**
     * 处理运行时异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:44
     */
    @ExceptionHandler(RuntimeException.class)
    public Response runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage());

        return Response.fail(ex.getMessage());
    }

    /**
     * 处理空指针异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:44
     */
    @ExceptionHandler(NullPointerException.class)
    public Response nullPointerExceptionHandler(NullPointerException ex) {
        log.error(ex.getMessage());

        return Response.fail(ex.getMessage());
    }

    /**
     * 处理认证异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Response accessDeniedExceptionHandler(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return Response.fail(ResultCodeEnum.UNAUTHORIZED.getCode(), ex.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return response
     */
    @ExceptionHandler({IgnoreException.class})
    public Response handleIgnoreException(IgnoreException e) {
        log.error(e.getMessage());
        return Response.fail(e.getStatus(), e.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return response
     */
    @ExceptionHandler({AccessException.class})
    public Response handleAccessException(AccessException e) {
        log.error(e.getMessage());
        return Response.fail(e.getStatus(), e.getMessage());
    }

    /**
     * 处理404异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    public Response requestNotAvailable(NoHandlerFoundException ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理404异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({RequestRejectedException.class})
    public Response requestNotAvailable(RequestRejectedException ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理403异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({HttpClientErrorException.Forbidden.class})
    public Response requestForbidden(HttpClientErrorException.Forbidden ex) {
        log.error(ex.getMessage());
        return Response.fail(ResultCodeEnum.AUTH_NOT_ENOUGH.getCode(), ex.getMessage());
    }

    /**
     * 处理401异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({HttpClientErrorException.Unauthorized.class})
    public Response requestForbidden(HttpClientErrorException.Unauthorized ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理400参数无法读取异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response requestNotReadable(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理400参数不匹配异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({TypeMismatchException.class})
    public Response requestTypeMismatch(TypeMismatchException ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理400缺少参数异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
    public Response requestMissingServletRequest(Exception ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理405不支持的请求方式异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response request405(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理415请求内容类型不支持异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error(ex.getMessage());
        return Response.fail(ex.getMessage());
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return Response.fail(message);
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler(BindException.class)
    public Response bindExceptionHandler(BindException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return Response.fail(message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是ConstraintViolationException
     *
     * @param ex 异常实体
     * @return com.gm.graduation.design.response.Response
     * @since 2020/11/22 20:45
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response constraintViolationExceptionHandler(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
        return Response.fail(message);
    }

}
