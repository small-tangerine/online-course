package com.course.admin.config.security.handler;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务类
 *
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     *
     * @return map
     */
    Map<String, ConfigAttribute> loadDataSource();
}
