package com.course.component.cache;

import cn.hutool.core.util.ObjectUtil;
import com.course.api.entity.Permission;
import com.course.api.vo.admin.PermissionVo;
import com.course.commons.enums.PermissionTypeEnum;
import com.course.service.service.PermissionService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
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

    private static final String PERMISSION_KEY = "permission:user:";
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
            userPermission.removeIf(item -> PermissionTypeEnum.BUTTON.equalsType(item.getTypeId()));
            Map<Integer, PermissionVo> permissionVoMap = userPermission.stream()
                    .map(item -> mapperFacade.map(item, PermissionVo.class))
                    .collect(Collectors.toMap(PermissionVo::getId, Function.identity()));
            List<PermissionVo> permissionVoList = permissionVoMap.values().stream().peek(item -> {
                Integer parentId = item.getParentId();
                if (Objects.isNull(parentId) || Objects.equals(0, parentId)) {
                    return;
                }
                PermissionVo permissionVo = permissionVoMap.get(parentId);
                // 将子级挂载在父级下
                if (Objects.nonNull(permissionVo)) {
                    List<PermissionVo> children = permissionVo.getChildren();
                    if (CollectionUtils.isNotEmpty(children)) {
                        children.add(item);
                    } else {
                        permissionVo.setChildren(Lists.newArrayList(item));
                    }
                    // 显示排序
                    permissionVo.getChildren().sort(Comparator.comparingInt(PermissionVo::getDisplayOrder));
                }
            }).filter(item -> Objects.equals(0, item.getParentId()))
                    .sorted(Comparator.comparingInt(PermissionVo::getDisplayOrder))
                    .collect(Collectors.toList());
            //权限
            permissionVoCache.put(key, new PermissionVo().setChildren(permissionVoList));
        }
    }

}
