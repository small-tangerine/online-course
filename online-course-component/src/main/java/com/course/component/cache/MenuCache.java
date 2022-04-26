package com.course.component.cache;

import cn.hutool.core.util.ObjectUtil;
import com.course.api.entity.Permission;
import com.course.api.vo.admin.MenuVo;
import com.course.commons.enums.PermissionTypeEnum;
import com.course.commons.utils.Assert;
import com.course.service.service.PermissionService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
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
 * @since 2022-03-06
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MenuCache {
    private final PermissionService permissionService;

    private static final Integer MENU_KEY = -1001;
    private Cache<Integer, MenuVo> permissionVoCache;

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
    public List<MenuVo> getPermissionVoList() {
        loadData();
        MenuVo parent = permissionVoCache.getIfPresent(MENU_KEY);
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
        if (Objects.nonNull(permissionVoCache.getIfPresent(MENU_KEY))) {
            return;
        }
        synchronized (MenuCache.class) {
            if (Objects.nonNull(permissionVoCache.getIfPresent(MENU_KEY))) {
                return;
            }
            List<Permission> allWithChildren = permissionService.findAllWithChildren(null);
            List<MenuVo> menuVoList = allWithChildren.stream().map(item -> {
                MenuVo menuVo = new MenuVo().setId(item.getId()).setLabel(item.getTitle())
                        .setParentId(item.getParentId()).setDisplayOrder(ObjectUtils.defaultIfNull(item.getDisplayOrder(),0));
                if (PermissionTypeEnum.BUTTON.equalsType(item.getTypeId())) {
                    menuVo.setPath(item.getPath());
                }
                return menuVo;
            }).collect(Collectors.toList());
            Map<Integer, MenuVo> permissionVoMap = menuVoList.stream()
                    .collect(Collectors.toMap(MenuVo::getId, Function.identity()));

            List<MenuVo> permissionVoList = permissionVoMap.values().stream().peek(item -> {
                Integer parentId = item.getParentId();
                if (Objects.isNull(parentId) || Objects.equals(0, parentId)) {
                    return;
                }
                MenuVo permissionVo = permissionVoMap.get(parentId);
                // 将子级挂载在父级下
                if (Objects.nonNull(permissionVo)) {
                    List<MenuVo> children = permissionVo.getChildren();
                    if (CollectionUtils.isNotEmpty(children)) {
                        children.add(item);
                    } else {
                        permissionVo.setChildren(Lists.newArrayList(item));
                    }
                    // 显示排序
                    permissionVo.getChildren().sort(Comparator.comparingInt(MenuVo::getDisplayOrder));
                }
            }).filter(item -> Objects.equals(0, item.getParentId()))
                    .sorted(Comparator.comparingInt(MenuVo::getDisplayOrder)).collect(Collectors.toList());
            //权限
            permissionVoCache.put(MENU_KEY, new MenuVo().setChildren(permissionVoList));

        }
    }

}
