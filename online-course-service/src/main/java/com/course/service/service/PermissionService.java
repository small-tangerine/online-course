package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.Permission;
import com.course.commons.enums.PermissionTypeEnum;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> findAllWithChildren(PermissionTypeEnum permissionTypeEnum);

    List<Permission> findByRoleId(Integer roleId);

    List<Permission> findUserPermission(Integer userId);

    Map<Integer,Permission> findByRoleIds(Collection<Integer> roleIds);

}
