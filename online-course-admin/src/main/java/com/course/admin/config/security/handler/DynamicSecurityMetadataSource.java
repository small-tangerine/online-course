package com.course.admin.config.security.handler;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.URLUtil;
import com.course.component.cache.PermissionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 动态权限数据源，用于获取动态权限规则
 *
 * @author macro
 * @date 2020/2/7
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @Autowired
    private PermissionCache permissionCache;


    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        permissionCache.expireAll();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);

        List<ConfigAttribute> configAttributes = new ArrayList<>();

        if (MapUtil.isEmpty(configAttributeMap)) {
            this.loadDataSource();
        }
        AntPathMatcher pathMatcher = new AntPathMatcher();

        //获取访问该路径所需资源
        for (Map.Entry<String, ConfigAttribute> pattern : configAttributeMap.entrySet()) {
            if (pathMatcher.match(pattern.getKey(), path)) {
                configAttributes.add(pattern.getValue());
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
