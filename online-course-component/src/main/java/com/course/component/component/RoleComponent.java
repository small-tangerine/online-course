package com.course.component.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.RolePermission;
import com.course.api.entity.UserRole;
import com.course.api.enums.RoleTypeEnum;
import com.course.service.service.RolePermissionService;
import com.course.service.service.RoleService;
import com.course.service.service.UserRoleService;
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
 * @since 2022-03-08
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class RoleComponent {
    private final RolePermissionService rolePermissionService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

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
        LambdaUpdateWrapper<UserRole> update = Wrappers.lambdaUpdate();
        update.in(UserRole::getRoleId, resourceCollect)
                .set(UserRole::getRoleId, RoleTypeEnum.STUDENT.getType());
        userRoleService.update(update);
    }
}
