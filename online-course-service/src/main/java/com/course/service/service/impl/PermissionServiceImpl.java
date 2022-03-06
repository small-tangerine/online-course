package com.course.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.api.entity.Permission;
import com.course.service.mapper.PermissionMapper;
import com.course.service.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Permission> findAllWithChildren() {
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
}
