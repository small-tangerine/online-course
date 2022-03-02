package com.course.commons.constant;

/**
 * @date 2020-09-10
 * @description 缓存键枚举
 */
public interface CacheKeyConstant {
    /**
     * 用户登录前缀
     */
    String USER_LOGIN_PREFIX = "template:login:{}:{}";

    /**
     * 用户权限菜单
     */
    String USER_PERMISSION_ROUTERS = "template:permission:routers:{}";

    String PERMISSION_ALL = "template:permission:all";
}
