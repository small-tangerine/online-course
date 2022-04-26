package com.course.server.config.security.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.course.api.entity.User;
import com.course.server.config.security.model.LoginUser;
import com.course.server.config.security.service.SecurityService;
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
        User user = userService.findByUsername(username);
        if (Objects.isNull(user)) {
            log.error("帐号【{}】不存在", username);
            throw new UsernameNotFoundException(CharSequenceUtil.format("用户帐号【{}】不存在", username));
        }
        return new LoginUser(user);
    }

}
