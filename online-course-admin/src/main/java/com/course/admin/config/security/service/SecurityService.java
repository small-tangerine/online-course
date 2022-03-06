package com.course.admin.config.security.service;


import com.course.api.vo.admin.PermissionVo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author panguangming
 */
public interface SecurityService  {
    /**
     * 根据用户账号获取信息
     *
     * @param userName 用户账号
     * @return UserDetails
     * @since 2020/11/22 00:48
     */
    UserDetails loadUserByUsername(String userName);

    /**
     * 获取所有权限访问资源
     *
     * @return permission
     * @since 2020/11/22 00:50
     */
    List<PermissionVo> queryAllPermissionResource();
}
