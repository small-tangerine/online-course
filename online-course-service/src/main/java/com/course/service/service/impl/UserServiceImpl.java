package com.course.service.service.impl;

import com.course.api.entity.User;
import com.course.service.mapper.UserMapper;
import com.course.service.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username)
                .or().eq(User::getMobile, username)
                .eq(User::getEmail, username)
                .last("limit 1")
                .one();
    }

    @Override
    public User findAdminByUsername(String username) {
        return baseMapper.findAdminByUsername(username);
    }
}
