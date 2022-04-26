package com.course.admin.config.security.service;


import org.springframework.security.core.userdetails.UserDetails;

/**
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

}
