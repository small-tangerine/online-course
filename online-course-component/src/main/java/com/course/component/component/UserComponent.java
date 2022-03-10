package com.course.component.component;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.entity.UserRole;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.LoginVo;
import com.course.api.vo.admin.PermissionVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.enums.SexEnum;
import com.course.commons.utils.IdUtils;
import com.course.api.entity.UserToken;
import com.course.component.cache.UserPermissionCache;
import com.course.service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    private final PermissionService permissionService;
    private final UserPermissionCache userPermissionCache;
    private final UserRoleService userRoleService;
    private final TeachersService teachersService;

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

        UserRole userRole = new UserRole().setUserId(defaultUser.getId())
                .setRoleId(RoleTypeEnum.STUDENT.getType())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(defaultUser.getId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(defaultUser.getId());
        userRoleService.save(userRole);
        userTokenService.save(userToken);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(User updatePassword, UserToken userToken) {
        userService.updateById(updatePassword);
        userTokenService.saveOrUpdate(userToken);
    }

    public List<PermissionVo> wrapUserPermissionRouters(Integer userId) {
        PermissionVo byId = userPermissionCache.getById(userId);
        if (Objects.isNull(byId)) {
            return Collections.emptyList();
        }
        return byId.getChildren();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUsrRole(UserRole updateUserRole) {
        LambdaQueryWrapper<UserRole> query = Wrappers.lambdaQuery();
        query.eq(UserRole::getUserId, updateUserRole.getUserId());
        userRoleService.remove(query);
        userRoleService.save(updateUserRole);
        // 角色是否讲师 用户讲师信息是否存在
        Integer roleId = updateUserRole.getRoleId();
        Integer userId = updateUserRole.getUpdatedBy();
        if (RoleTypeEnum.TEACHER.equalsStatus(roleId)) {
            Teachers byUserId = teachersService.getByUserId(userId);
            if (Objects.isNull(byUserId)) {
                Teachers saveTeacher = new Teachers().setUserId(updateUserRole.getUserId())
                        .setAvatar(CommonConstant.DEFAULT_AVATAR)
                        .setUpdatedBy(userId).setUpdatedAt(LocalDateTime.now())
                        .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
                teachersService.save(saveTeacher);
            }
        }
    }
}
