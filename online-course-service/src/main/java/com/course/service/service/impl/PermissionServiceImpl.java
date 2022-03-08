package com.course.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.api.entity.Permission;
import com.course.commons.enums.PermissionTypeEnum;
import com.course.service.mapper.PermissionMapper;
import com.course.service.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findAllWithChildren(PermissionTypeEnum permissionTypeEnum) {
        if (Objects.nonNull(permissionTypeEnum)) {
            return lambdaQuery().eq(Permission::getTypeId, permissionTypeEnum.getType()).list();
        }
        return lambdaQuery().list();
    }

    @Override
    public List<Permission> findByRoleId(Integer roleId) {
        return baseMapper.findByRoleId(roleId);
    }

    @Override
    public List<Permission> findUserPermission(Integer userId) {
        return baseMapper.findUserPermission(userId);
    }

    @Override
    public Map<Integer, Permission> findByRoleIds(Collection<Integer> roleIds) {
        List<Permission> byRoleIds = baseMapper.findByRoleIds(roleIds);
        return byRoleIds.stream().collect(Collectors.toMap(Permission::getId, Function.identity()));
    }
}
