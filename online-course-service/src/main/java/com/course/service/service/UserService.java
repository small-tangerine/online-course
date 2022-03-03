package com.course.service.service;

import com.course.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserService extends IService<User> {

    /**
     * 根据账号查找用户
     * @param username 账号
     * @return user
     */
    User findByUsername(String username);
}
