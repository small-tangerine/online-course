package com.course.component.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.RolePermission;
import com.course.service.service.RolePermissionService;
import com.course.service.service.RoleService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 角色组件
 *
 * @author panguangming
 * @since 2022-03-08
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class RoleComponent {
    private final RolePermissionService rolePermissionService;
    private final RoleService roleService;

    @Transactional(rollbackFor = Exception.class)
    public void updateRolePermission(Integer id, List<RolePermission> collect) {
        rolePermissionService.removeByMap(ImmutableMap.of("role_id", id));
        rolePermissionService.saveBatch(collect);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeRole(Collection<Integer> resourceCollect) {
        roleService.removeByIds(resourceCollect);
        LambdaQueryWrapper<RolePermission> remove = Wrappers.lambdaQuery();
        remove.in(RolePermission::getRoleId, resourceCollect);
        rolePermissionService.remove(remove);
    }
}
