package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.admin.config.security.handler.DynamicSecurityMetadataSource;
import com.course.api.entity.Permission;
import com.course.api.vo.admin.PermissionVo;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.component.cache.MenuCache;
import com.course.component.cache.PermissionCache;
import com.course.component.cache.UserPermissionCache;
import com.course.component.component.PermissionComponent;
import com.course.service.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 权限菜单
 *
 * @since 2022-03-16
 */
@RequestMapping("/permission")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class PermissionController {

    private final PermissionService permissionService;
    private final MapperFacade mapperFacade;
    private final PermissionComponent permissionComponent;
    private final MenuCache menuCache;
    private final PermissionCache permissionCache;
    private final UserPermissionCache userPermissionCache;
    private final DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    /**
     * 权限菜单列表
     *
     * @param page     页码
     * @param pageSize 页大小
     * @param keyword  关键词
     * @param type     类型
     * @return response
     */
    @GetMapping("/list")
    public Response pageQueryList(Integer page, Integer pageSize, String keyword, Integer type) {
        LambdaQueryWrapper<Permission> query = Wrappers.lambdaQuery();
        query.eq(Objects.nonNull(type), Permission::getTypeId, type)
                .like(StringUtils.isNotBlank(keyword), Permission::getTitle, StringUtils.trim(keyword))
                .orderByAsc(Permission::getParentId)
                .orderByAsc(Permission::getDisplayOrder)
                .orderByDesc(Permission::getId);
        List<Permission> list = permissionService.list(query);
        return Response.ok(list);
    }

    /**
     * 新增权限菜单
     *
     * @param permissionVo 权限实体
     * @return response
     */
    @PostMapping("/create")
    public Response permissionsCreate(@RequestBody PermissionVo permissionVo) {
        Permission map = mapperFacade.map(permissionVo, Permission.class);
        map.setId(null)
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedBy(SecurityUtils.getUserId()).setUpdatedAt(LocalDateTime.now());
        permissionService.save(map);
        // 清除缓存
        menuCache.expireAll();
        userPermissionCache.expireAll();
        dynamicSecurityMetadataSource.clearDataSource();
        return ResponseHelper.createSuccess();
    }

    /**
     * 更新权限菜单
     *
     * @param permissionVo 权限实体
     * @return response
     */
    @PostMapping("/update")
    public Response permissionsUpdate(@RequestBody PermissionVo permissionVo) {
        Integer id = permissionVo.getId();
        Assert.notNull(id, "权限菜单编号不能为空");
        Permission byId = permissionService.getById(id);
        Assert.notNull(byId, "权限菜单不存在");
        Permission map = mapperFacade.map(permissionVo, Permission.class);
        map.setId(id).setUpdatedBy(SecurityUtils.getUserId()).setUpdatedAt(LocalDateTime.now());
        permissionService.updateById(map);
        // 清除缓存
        menuCache.expireAll();
        userPermissionCache.expireAll();
        dynamicSecurityMetadataSource.clearDataSource();
        return ResponseHelper.updateSuccess();
    }

    /**
     * 删除权限菜单
     *
     * @param permissionVo 权限实体
     * @return response
     */
    @PostMapping("/delete")
    public Response permissionsDelete(@RequestBody PermissionVo permissionVo) {
        Collection<Integer> ids = permissionVo.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseHelper.deleteSuccess();
        }
        permissionComponent.delete(ids);
        // 删除缓存
        menuCache.expireAll();
        userPermissionCache.expireAll();
        dynamicSecurityMetadataSource.clearDataSource();
        return ResponseHelper.deleteSuccess();
    }

    /**
     * 修改权限菜单隐藏状态
     *
     * @param permissionVo 权限实体
     * @return response
     */
    @PostMapping("/hidden-status")
    public Response permissionsHiddenStatus(@RequestBody PermissionVo permissionVo) {
        Integer id = permissionVo.getId();
        Assert.notNull(id, "权限菜单编号不能为空");
        Permission byId = permissionService.getById(id);
        Assert.notNull(byId, "权限菜单不存在");
        Permission permission = new Permission().setId(id).setIsHidden(YesOrNoEnum.YES.equalsStatus(byId.getIsHidden()) ?
                YesOrNoEnum.NO.getValue() : YesOrNoEnum.YES.getValue())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        permissionService.updateById(permission);
        // 清除缓存
        menuCache.expireAll();
        userPermissionCache.expireAll();
        dynamicSecurityMetadataSource.clearDataSource();
        return ResponseHelper.updateSuccess();
    }
}
