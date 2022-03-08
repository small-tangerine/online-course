package com.course.admin.config.security.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.course.admin.config.security.model.LoginUser;
import com.course.admin.config.security.service.SecurityService;
import com.course.api.entity.Permission;
import com.course.api.entity.User;
import com.course.service.service.PermissionService;
import com.course.service.service.UserRoleService;
import com.course.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final PermissionService permissionService;
    private final UserRoleService userRoleService;
    private final MapperFacade mapperFacade;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //从数据库找
        User user = userService.findAdminByUsername(username);
        if (Objects.isNull(user)) {
            log.error("帐号【{}】不存在", username);
            throw new UsernameNotFoundException(CharSequenceUtil.format("用户帐号【{}】不存在", username));
        }
        Integer roleId = userRoleService.getIdByUserId(user.getId());
        Set<String> permissionTag = listPermissionTagByRoleId(roleId);
        return new LoginUser(user, permissionTag, roleId);
    }


    private Set<String> listPermissionTagByRoleId(Integer roleId) {
        List<Permission> permissionList = permissionService.findByRoleId(roleId);
        return permissionList.stream()
                .map(Permission::getPermissionTag)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
    }

}
