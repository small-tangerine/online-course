package com.course.commons.model;

import lombok.Data;

/**
 * 响应封装
 *
 * @author panguangming
 * @since 2022-01-24
 */
@Data
public class Response<T> {
    private Integer error;
    private String msg;
    private T data;

    public static <T> Response<T> ok(T data) {
        Response<T> res = new Response<>();
        res.setError(0);
        res.setMsg("success");
        res.setData(data);
        return res;
    }

    public static <T> Response<T> ok() {
        Response<T> res = new Response<>();
        res.setError(0);
        res.setMsg("success");
        return res;
    }

    public static <T> Response<T> ok(String msg, T data) {
        Response<T> res = new Response<>();
        res.setError(0);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static <T> Response<T> ok(String msg) {
        Response<T> res = new Response<>();
        res.setError(0);
        res.setMsg(msg);
        return res;
    }

    /**
     * 错误消息
     *
     * @param msg 错误信息
     * @return 实体
     */
    public static <T> Response<T> fail(String msg) {
        Response<T> res = new Response<>();
        res.setError(500);
        res.setMsg(msg);
        return res;
    }

    public static <T> Response<T> fail(Integer error, String msg) {
        Response<T> res = new Response<>();
        res.setError(error);
        res.setMsg(msg);
        return res;
    }

}
