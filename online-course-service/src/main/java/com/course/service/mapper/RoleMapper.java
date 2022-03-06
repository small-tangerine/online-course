package com.course.service.mapper;

import com.course.api.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role findUserRole(@Param("userId") Integer userId);
}
