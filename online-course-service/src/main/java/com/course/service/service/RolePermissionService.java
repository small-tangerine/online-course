package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.RolePermission;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色权限关联 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface RolePermissionService extends IService<RolePermission> {
    Map<Integer, List<RolePermission>> findIdByRoleIds(Collection<Integer> roleIds);
}
