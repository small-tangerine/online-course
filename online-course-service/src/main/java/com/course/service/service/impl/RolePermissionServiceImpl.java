package com.course.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.api.entity.RolePermission;
import com.course.service.mapper.RolePermissionMapper;
import com.course.service.service.RolePermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限关联 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public Map<Integer, List<RolePermission>> findIdByRoleIds(Collection<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)){
            return Collections.emptyMap();
        }
        return lambdaQuery().in(RolePermission::getRoleId,roleIds)
                .list().stream().collect(Collectors.groupingBy(RolePermission::getRoleId));
    }
}
