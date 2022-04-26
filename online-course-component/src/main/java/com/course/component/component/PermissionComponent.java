package com.course.component.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Permission;
import com.course.api.entity.RolePermission;
import com.course.service.service.PermissionService;
import com.course.service.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * 权限
 *
 * @since 2022-03-16
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class PermissionComponent {

    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;

    @Transactional(rollbackFor = Exception.class)
    public void delete(Collection<Integer> ids) {
        LambdaQueryWrapper<Permission> queryPermission = Wrappers.lambdaQuery();
        queryPermission.in(Permission::getId, ids)
                .or().in(Permission::getParentId, ids);
        permissionService.remove(queryPermission);
        LambdaQueryWrapper<RolePermission> query = Wrappers.lambdaQuery();
        query.in(RolePermission::getPermissionId, ids);
        rolePermissionService.remove(query);
    }
}
