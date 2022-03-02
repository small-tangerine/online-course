package com.course.config.security.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.User;
import com.course.config.security.model.LoginUser;
import com.course.config.security.service.SecurityService;
import com.course.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 安全验证操作
 *
 * @author guangming
 * @version 1.0
 * @since 2020/10/15/015 9:40
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //从数据库找
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.eq(User::getEmail, username)
                .or().eq(User::getMobile, username)
                .last("limit 1");
        User user = userService.getOne(query);
        if (Objects.isNull(user)) {
            log.error("帐号【{}】不存在", username);
            throw new UsernameNotFoundException(CharSequenceUtil.format("用户帐号【{}】不存在", username));
        }
        return new LoginUser(user);
    }

}
