package com.course.component;

import cn.hutool.core.lang.Validator;
import com.course.api.entity.User;
import com.course.api.vo.LoginVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.enums.SexEnum;
import com.course.commons.utils.IdUtils;
import com.course.service.entity.UserToken;
import com.course.service.service.UserService;
import com.course.service.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 用户组件
 *
 * @author panguangming
 * @since 2022-01-24
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class UserComponent {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserTokenService userTokenService;

    /**
     * 封装用户注册默认信息
     *
     * @param loginVo 注册实体
     * @return user
     */
    public User wrapDefaultUserInfo(LoginVo loginVo) {
        String encodePassword = passwordEncoder.encode(loginVo.getPassword());
        User user = new User().setUsername(loginVo.getUsername())
                .setPassword(encodePassword)
                .setSex(SexEnum.UN_KNOW.getSex())
                .setUid(IdUtils.get16UUID())
                .setAvatar(CommonConstant.DEFAULT_AVATAR)
                .setNickname("用户" + System.currentTimeMillis())
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());
        if (Validator.isMobile(loginVo.getUsername())) {
            user.setMobile(loginVo.getUsername());
        }
        if (Validator.isEmail(loginVo.getUsername())) {
            user.setEmail(loginVo.getUsername());
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUser(User defaultUser, UserToken userToken) {
        userService.save(defaultUser);
        userToken.setUserId(defaultUser.getId());
        userTokenService.save(userToken);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(User updatePassword, UserToken userToken) {
        userService.updateById(updatePassword);
        userTokenService.saveOrUpdate(userToken);
    }
}
