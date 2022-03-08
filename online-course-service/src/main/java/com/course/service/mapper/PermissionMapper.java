package com.course.service.mapper;

import com.course.api.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 菜单权限 Mapper 接口
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByRoleId(@Param("roleId") Integer roleId);

    List<Permission> findUserPermission(@Param("userId") Integer userId);

    List<Permission> findByRoleIds(@Param("list") Collection<Integer> roleIds);
}
