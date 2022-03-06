package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.Permission;

import java.util.List;

/**
 * <p>
 * 菜单权限 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> findAllWithChildren();

    List<Permission> findByRoleId(Integer roleId);

    List<Permission> findUserPermission(Integer userId);
}
