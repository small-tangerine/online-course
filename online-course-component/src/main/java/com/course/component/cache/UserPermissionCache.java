package com.course.component.cache;

import cn.hutool.core.util.ObjectUtil;
import com.course.api.entity.Permission;
import com.course.api.vo.admin.PermissionVo;
import com.course.service.service.PermissionService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 权限缓存
 *
 * @author panguangming
 * @since 2022-03-06
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserPermissionCache {
    private final PermissionService permissionService;
    private final MapperFacade mapperFacade;

    private static final String PERMISSION_KEY = "permission:user";
    private Cache<String, PermissionVo> permissionVoCache;

    @PostConstruct
    public void init() {
        permissionVoCache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofDays(15))
                .build();
    }

    /**
     * 通过id获取数据
     */
    public PermissionVo getById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }
        loadData(id);
        return ObjectUtil.cloneByStream(permissionVoCache.getIfPresent(PERMISSION_KEY + id));
    }

    /**
     * 缓存过期
     */
    public void expireAll() {
        permissionVoCache.invalidateAll();
    }

    /**
     * 加载数据
     */
    public void loadData(Integer userId) {
        String key = PERMISSION_KEY + userId;
        if (Objects.nonNull(permissionVoCache.getIfPresent(key))) {
            return;
        }
        synchronized (UserPermissionCache.class) {
            if (Objects.nonNull(permissionVoCache.getIfPresent(key))) {
                return;
            }
            List<Permission> userPermission = permissionService.findUserPermission(userId);
            List<PermissionVo> permissionVoList = userPermission.stream()
                    .map(item -> mapperFacade.map(item, PermissionVo.class))
                    .collect(Collectors.toList());
            //权限
            permissionVoCache.put(PERMISSION_KEY, new PermissionVo().setChildren(permissionVoList));
        }
    }

}
