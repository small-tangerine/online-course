package com.course.service.service;

import com.course.api.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface RoleService extends IService<Role> {

    Role findUserRole(Integer userId);
}
