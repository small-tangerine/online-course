package com.course.commons.utils;

import com.course.commons.model.Response;

/**
 * @date 2020-05-13
 * @Description: response返回结果工具类
 */
public class ResponseHelper {

    private ResponseHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static Response updateSuccess() {
        return Response.ok("修改成功");
    }

    public static Response deleteSuccess() {
        return Response.ok("删除成功");
    }

    public static Response createSuccess() {
        return Response.ok("创建成功");
    }

    public static <T> Response<T> createSuccess(T t) {
        return Response.ok("创建成功", t);
    }

    public static Response operateSuccess() {
        return Response.ok("操作成功");
    }

    public static <T> Response operateSuccess(T t) {
        return Response.ok("操作成功", t);
    }
}
