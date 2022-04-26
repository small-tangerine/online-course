package com.course.component.cache;

import cn.hutool.core.util.ObjectUtil;
import com.course.api.entity.Permission;
import com.course.api.vo.admin.PermissionVo;
import com.course.commons.enums.PermissionTypeEnum;
import com.course.commons.utils.Assert;
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
 * @since 2022-03-06
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PermissionCache {
    private final PermissionService permissionService;
    private final MapperFacade mapperFacade;

    private static final Integer PERMISSION_KEY = -1000;
    private Cache<Integer, PermissionVo> permissionVoCache;

    @PostConstruct
    public void init() {
        permissionVoCache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofDays(15))
                .build();
    }

    /**
     * 返回权限包装数据
     *
     * @return 权限
     */
    public List<PermissionVo> getPermissionVoList() {
        loadData();
        PermissionVo parent = permissionVoCache.getIfPresent(PERMISSION_KEY);
        Assert.notNull(parent, "无效的缓存");
        return ObjectUtil.cloneByStream(parent.getChildren());
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
    public void loadData() {
        if (Objects.nonNull(permissionVoCache.getIfPresent(PERMISSION_KEY))) {
            return;
        }
        synchronized (PermissionCache.class) {
            if (Objects.nonNull(permissionVoCache.getIfPresent(PERMISSION_KEY))) {
                return;
            }
            List<Permission> allWithChildren = permissionService.findAllWithChildren(PermissionTypeEnum.BUTTON);
            List<PermissionVo> permissionVoList = allWithChildren.stream()
                    .map(item -> mapperFacade.map(item, PermissionVo.class))
                    .collect(Collectors.toList());
            List<PermissionVo> permissionVos = permissionVoList.stream()
                    .peek(permissionVo -> permissionVoCache.put(permissionVo.getId(), permissionVo))
                    .collect(Collectors.toList());
            //权限
            permissionVoCache.put(PERMISSION_KEY, new PermissionVo().setChildren(permissionVos));

        }
    }

}
