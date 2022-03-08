package com.course.admin.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.CharSequenceUtil;
import com.course.admin.config.security.JwtUtil;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.*;
import com.course.api.enums.LoginTypeEnum;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.LoginVo;
import com.course.api.vo.admin.PermissionVo;
import com.course.api.vo.server.UserVo;
import com.course.commons.annotations.Forget;
import com.course.commons.annotations.Login;
import com.course.commons.enums.PermissionTypeEnum;
import com.course.commons.exception.MyAuthenticationException;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.component.component.UserComponent;
import com.course.service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户控制器
 *
 * @author panguangming
 * @since 2022-03-02
 */
@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AccountController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MapperFacade mapperFacade;
    private final JwtUtil jwtUtil;
    private final UserTokenService userTokenService;
    private final UserComponent userComponent;
    private final RoleService roleService;
    private final TeachersService teachersService;
    private final PermissionService permissionService;

    /**
     * 登录接口
     *
     * @param loginVo 登录vo
     * @return response
     */
    @PostMapping("/login")
    public Response login(@RequestBody @Validated(Login.class) LoginVo loginVo) {
        // 校验用户账号密码是否正确
        User user = userService.findAdminByUsername(loginVo.getUsername());
        if (Objects.isNull(user)) {
            log.error(CharSequenceUtil.format("用户【{}】不存在", loginVo.getUsername()));
            //对用户隐藏不存在 抛出账号或密码的异常
            throw new MyAuthenticationException("用户帐号或密码错误");
        }
        Assert.isTrue(passwordEncoder.matches(loginVo.getPassword(), user.getPassword()),
                "用户帐号或密码错误");
        UserVo map = mapperFacade.map(user, UserVo.class);

        map.setPassword(null);
        // 生成token
        UserToken userToken = generateToken(user);
        UserToken loginToken = userTokenService.findByUserIdAndType(user.getId(), LoginTypeEnum.BACKEND.getTypeId());
        if (Objects.nonNull(loginToken)) {
            userToken.setId(loginToken.getId());
        }
        // 更新登录状态
        userTokenService.saveOrUpdate(userToken);
        Teachers teacher = getTeacherInfo(user.getId());
        if (Objects.nonNull(teacher)) {
            map.setAvatar(teacher.getAvatar());
        }
        map.setToken(userToken.getToken());
        return Response.ok(map);
    }

    @GetMapping("/info")
    public Response accountInfo() {
        Integer userId = SecurityUtils.getUserId();
        User byId = userService.getById(userId);
        UserVo map = mapperFacade.map(byId, UserVo.class);
        map.setPassword(null);
        Role role = roleService.findUserRole(userId);
        if (Objects.nonNull(role)) {
            map.setRoleId(role.getId()).setRoleTitle(role.getTitle());
        }
        Teachers teacherInfo = getTeacherInfo(userId);
        if (Objects.nonNull(teacherInfo)) {
            map.setTeacherInfo(teacherInfo)
                    .setAvatar(teacherInfo.getAvatar());
        }
        List<Permission> userPermission = permissionService.findUserPermission(userId);
        Set<String> collect = userPermission.stream().filter(item -> PermissionTypeEnum.BUTTON.equalsType(item.getTypeId()))
                .map(Permission::getPermissionTag)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
        map.setRoles(collect);
        return Response.ok(map);
    }

    /**
     * 退出登录
     *
     * @return response
     */
    @PostMapping("/logout")
    public Response logout() {
        Integer userId = SecurityUtils.getUserId();
        UserToken userToken = userTokenService.findByUserIdAndType(userId, LoginTypeEnum.BACKEND.getTypeId());
        if (Objects.nonNull(userToken)) {
            userTokenService.removeById(userToken.getId());
        }
        return Response.ok("退出成功");
    }

    /**
     * 重置密码
     *
     * @param loginVo 重置密码
     * @return response
     */
    @PostMapping("/reset-password")
    public Response resetPassword(@RequestBody @Validated(Forget.class) LoginVo loginVo) {
        // 校验账号合法性
        Assert.isTrue(Validator.isEmail(loginVo.getUsername())
                || Validator.isMobile(loginVo.getUsername()), "请输入正确的手机号/邮箱");

        User user = userService.findByUsername(loginVo.getUsername());
        Assert.notNull(user, "该账号不存在,请联系管理员");
        Assert.equals(loginVo.getCheckPassword(), loginVo.getPassword(), "两次密码输入不一致");
        Assert.isFalse(passwordEncoder.matches(loginVo.getPassword(), user.getPassword()),
                "新密码不能与旧密码相同");

        // 更新登录状态
        User updatePassword = new User().setId(user.getId()).setPassword(passwordEncoder.encode(loginVo.getPassword()));
        userService.updateById(updatePassword);
        return Response.ok("重置密码成功");
    }

    /**
     * 用户路由菜单
     *
     * @return response
     */
    @GetMapping("/permission-routers")
    public Response userPermissionRouters() {
        Integer userId = SecurityUtils.getUserId();
        List<PermissionVo> permissionVo = userComponent.wrapUserPermissionRouters(userId);
        return Response.ok(permissionVo);
    }

    /**
     * 根据用户信息生成token信息
     *
     * @param user 用户信息
     * @return 用户token信息
     */
    private UserToken generateToken(User user) {
        String token = jwtUtil.generateToken(user);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusMinutes(30L);
        return new UserToken().setToken(token).setUserId(user.getId())
                .setUsername(user.getUsername()).setLoginAt(now)
                .setType(LoginTypeEnum.BACKEND.getTypeId())
                .setExpiredAt(expired);
    }


    private Teachers getTeacherInfo(Integer userId) {
        Role userRole = roleService.findUserRole(userId);
        if (Objects.isNull(userRole)) {
            return null;
        }
        if (RoleTypeEnum.TEACHER.equalsStatus(userRole.getId())) {
            Teachers teacher = teachersService.getByUserId(userId);
            if (Objects.isNull(teacher)) {
                return null;
            }
            return teacher;
        }
        return null;
    }

}
