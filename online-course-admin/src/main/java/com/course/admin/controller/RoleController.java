package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.admin.config.security.handler.DynamicSecurityMetadataSource;
import com.course.api.entity.Role;
import com.course.api.entity.RolePermission;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.admin.RoleVo;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.component.cache.MenuCache;
import com.course.component.cache.UserPermissionCache;
import com.course.component.component.RoleComponent;
import com.course.service.service.RolePermissionService;
import com.course.service.service.RoleService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色
 *
 * @author panguangming
 * @since 2022-03-08
 */
@RequestMapping("/role")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class RoleController {
    private final RoleService roleService;
    private final RolePermissionService rolePermissionService;
    private final MenuCache menuCache;
    private final MapperFacade mapperFacade;
    private final DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
    private final UserPermissionCache userPermissionCache;
    private final RoleComponent roleComponent;

    /**
     * 角色列表
     *
     * @param page     页码
     * @param pageSize 页大小
     * @param keyword  关键词
     * @return response
     */
    @GetMapping("/list")
    public Response RoleList(Integer page, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Role> query = Wrappers.lambdaQuery();
        query.like(StringUtils.isNotBlank(keyword), Role::getTitle, StringUtils.trim(keyword))
                .or().like(StringUtils.isNotBlank(keyword), Role::getNote, StringUtils.trim(keyword)).orderByAsc(Role::getId);
        Paging<Role> paging = new Paging<>(page, pageSize);
        roleService.page(paging, query);
        Set<Integer> roleIds = paging.getItems().stream().map(Role::getId).collect(Collectors.toSet());
        Map<Integer, List<RolePermission>> permissionIdsMap = rolePermissionService.findIdByRoleIds(roleIds);
        paging.convert(item -> {
            RoleVo map = mapperFacade.map(item, RoleVo.class);
            List<RolePermission> rolePermissions = permissionIdsMap.get(item.getId());
            if (CollectionUtils.isNotEmpty(rolePermissions)) {
                map.setResourceCollect(rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet()));
            }
            return map;
        });
        return Response.ok(paging);
    }

    @PostMapping("/update")
    public Response roleUpdate(@RequestBody Role role) {
        Integer id = role.getId();
        String note = role.getNote();
        String title = role.getTitle();
        Assert.notNull(id, "角色编号不能为空");
        Assert.notBlank(title, "角色名称不能为空");
        Role byId = roleService.getById(id);
        Assert.notNull(byId, "角色不存在");
        if (RoleTypeEnum.containsStatus(id)) {
            Assert.equals(byId.getTitle(), title, "基础角色不允许修改名称");
        }

        LambdaQueryWrapper<Role> query = Wrappers.lambdaQuery();
        query.ne(Role::getId, id).eq(Role::getTitle, StringUtils.trim(title));
        List<Role> list = roleService.list(query);
        Assert.isTrue(Objects.equals(list.size(), 0), "该角色名称已被占用");

        Role updateRole = new Role().setId(id).setNote(note).setTitle(title)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        roleService.updateById(updateRole);
        return ResponseHelper.updateSuccess();
    }

    /**
     * 菜单列表用于分配权限
     */
    @GetMapping("/menu-list")
    public Response roleMenuList() {
        return Response.ok(menuCache.getPermissionVoList());
    }

    /**
     * 菜单列表用于分配权限
     */
    @GetMapping("/menu-cache-clean")
    public Response roleMenuCacheClean() {
        menuCache.expireAll();
        return Response.ok("权限菜单缓存清理成功");
    }


    @PostMapping("/permission-scope")
    public Response rolePermissionScope(@RequestBody RoleVo roleVo) {
        Integer id = roleVo.getId();
        Assert.notNull(id, "角色编号不能为空");
        Role byId = roleService.getById(id);
        Assert.notNull(byId, "角色不存在");
        Collection<Integer> resourceCollect = roleVo.getResourceCollect();
        if (CollectionUtils.isEmpty(resourceCollect)) {
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
            map.put("role_id", id);
            rolePermissionService.removeByMap(map);
        }
        List<RolePermission> collect = resourceCollect.stream()
                .distinct()
                .map(item -> new RolePermission().setPermissionId(item)
                        .setRoleId(id)
                        .setCreatedAt(LocalDateTime.now())
                        .setCreatedBy(SecurityUtils.getUserId())).collect(Collectors.toList());
        Assert.equals(collect.size(), resourceCollect.size(), "请勿重复勾选");

        roleComponent.updateRolePermission(id, collect);
        userPermissionCache.expireAll();
        dynamicSecurityMetadataSource.clearDataSource();
        return Response.ok("分配权限成功");
    }

    @PostMapping("/delete")
    public Response roleDelete(@RequestBody RoleVo roleVo) {
        Collection<Integer> resourceCollect = roleVo.getResourceCollect();
        if (CollectionUtils.isEmpty(resourceCollect)) {
            return ResponseHelper.deleteSuccess();
        }
        resourceCollect.forEach(item -> Assert.isFalse(RoleTypeEnum.containsStatus(item), "基础角色禁止删除"));
        roleComponent.removeRole(resourceCollect);
        return ResponseHelper.deleteSuccess();
    }

    @PostMapping("/create")
    public Response roleCreate(@RequestBody Role role) {
        String title = role.getTitle();
        String note = role.getNote();
        Assert.notBlank(title, "角色名称不能为空");
        LambdaQueryWrapper<Role> query = Wrappers.lambdaQuery();
        query.eq(Role::getTitle, StringUtils.trim(title));
        List<Role> list = roleService.list(query);
        Assert.isTrue(Objects.equals(list.size(), 0), "该角色名称已被占用");
        Role createRole = new Role().setTitle(title).setNote(note)
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        roleService.save(createRole);
        return ResponseHelper.createSuccess();
    }
}
