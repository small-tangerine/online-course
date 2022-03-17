package com.course.admin.controller;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.Role;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.entity.UserRole;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.admin.UserVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.IdUtils;
import com.course.commons.utils.ResponseHelper;
import com.course.component.cache.UserPermissionCache;
import com.course.component.component.UserComponent;
import com.course.service.service.RoleService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserRoleService;
import com.course.service.service.UserService;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author panguangming
 * @since 2022-03-09
 */
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final MapperFacade mapperFacade;
    private final UserRoleService userRoleService;
    private final UserComponent userComponent;
    private final UserPermissionCache userPermissionCache;
    private final TeachersService teachersService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public Response UserList(Integer page, Integer pageSize, String keyword, Integer roleId) {
        Paging<User> paging = new Paging<>(page, pageSize);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        if (StringUtils.isNotBlank(keyword)) {
            map.put("keyword", StringUtils.trim(keyword));
        }
        map.put("role_id", roleId);
        userService.findByMap(paging, map);
        Set<Integer> userIds = paging.getItems().stream().map(User::getId).collect(Collectors.toSet());
        Map<Integer, Integer> userRoleMap = userRoleService.findMapByUserIds(userIds);
        Set<Integer> roleIds = Sets.newHashSet();
        if (MapUtils.isNotEmpty(userRoleMap)) {
            roleIds.addAll(userRoleMap.values());
        }
        Map<Integer, String> roleMap = roleService.listByIds(roleIds).stream().collect(Collectors.toMap(Role::getId, Role::getTitle));
        paging.convert(item -> {
            UserVo userVo = mapperFacade.map(item, UserVo.class);
            Integer integer = userRoleMap.get(item.getId());
            if (Objects.nonNull(integer)) {
                String roleTitle = roleMap.get(integer);
                if (StringUtils.isNotBlank(roleTitle)) {
                    userVo.setRoleTitle(roleTitle).setRoleId(integer);
                }
            }
            return userVo;
        });
        return Response.ok(paging);
    }

    @GetMapping("/role-select")
    public Response userRoleSelect() {
        List<Role> list = roleService.list();
        return Response.ok(list.stream().map(item -> {
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
            map.put("roleId", item.getId());
            map.put("title", item.getTitle());
            return map;
        }).collect(Collectors.toList()));
    }

    @PostMapping("/assign-role")
    public Response userAssignRole(@RequestBody UserRole userRole) {
        Integer userId = userRole.getUserId();
        Assert.notNull(userId, "用户编号不能为空");
        User byId = userService.getById(userId);
        Assert.notNull(byId, "用户不存在");
        Integer roleId = userRole.getRoleId();
        Assert.notNull(roleId, "角色编号不能为空");
        Role role = roleService.getById(roleId);
        Assert.notNull(role, "角色不存在");
        LambdaQueryWrapper<UserRole> query = Wrappers.lambdaQuery();
        query.eq(UserRole::getUserId, userId)
                .eq(UserRole::getRoleId, roleId)
                .last("limit 1");
        UserRole one = userRoleService.getOne(query);
        if (Objects.nonNull(one)) {
            if (Objects.equals(one.getRoleId(), roleId)) {
                return Response.ok("分配角色成功");
            }
        }
        UserRole updateUserRole = new UserRole().setUserId(userId).setRoleId(roleId)
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());

        userComponent.updateUsrRole(updateUserRole);
        userPermissionCache.expireAll();
        return Response.ok("分配角色成功");
    }

    @GetMapping("/teacher-info")
    public Response userTeacherInfo(@NotNull(message = "用户编号不能为空") Integer id) {
        Role userRole = roleService.findUserRole(id);
        Assert.isTrue(RoleTypeEnum.TEACHER.equalsStatus(userRole.getId()), "该用户不是讲师");
        Teachers byUserId = teachersService.getByUserId(id);
        Assert.notNull(byUserId, "讲师信息不存在");
        return Response.ok(byUserId);
    }

    @PostMapping("/delete")
    public Response userDelete(@RequestBody UserVo user) {
        Collection<Integer> ids = user.getIds();
        Assert.notEmpty(ids, "请选择要删除的用户");
        Map<Integer, Integer> mapByUserIds = userRoleService.findMapByUserIds(ids);
        ids.forEach(item -> {
            Integer roleId = mapByUserIds.get(item);
            Assert.isFalse(RoleTypeEnum.ADMIN.equalsStatus(roleId), "用户【{}】为管理员不允许被删除", item);
        });
        Set<Integer> userIds = new HashSet<>(ids);
        Assert.equals(userIds.size(), ids.size(), "请勿重复勾选要删除的用户");
        userService.removeByIds(userIds);
        return ResponseHelper.deleteSuccess();
    }

    @PostMapping("/reset-password")
    public Response resetPassword(@RequestBody UserVo userVo) {
        Collection<Integer> ids = userVo.getIds();
        Assert.notEmpty(ids, "请选择要重置密码的用户");
        List<User> users = userService.listByIds(ids);
        List<User> userList = users.stream().map(item -> {
            Assert.notNull(item.getId(), "用户不存在");
            return new User().setId(item.getId()).setPassword(passwordEncoder.encode("123456"))
                    .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        }).collect(Collectors.toList());
        userService.updateBatchById(userList);
        return Response.ok("重置密码成功");
    }

    @PostMapping("/update")
    public Response userUpdate(@RequestBody UserVo userVo) {

        UserVo checkParams = checkParams(userVo);
        User user = mapperFacade.map(checkParams, User.class);
        user.setUpdatedBy(SecurityUtils.getUserId()).setUpdatedAt(LocalDateTime.now());

        Role roleServiceUserRole = roleService.findUserRole(checkParams.getId());
        UserRole userRole = null;
        if (!Objects.equals(checkParams.getRoleId(), roleServiceUserRole.getId())) {
            userRole = new UserRole().setUserId(checkParams.getId()).setRoleId(checkParams.getRoleId())
                    .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        }
        userComponent.updateUser(user, userRole);

        return ResponseHelper.updateSuccess();
    }

    private UserVo checkParams(UserVo userVo) {
        String username = userVo.getUsername();
        Assert.isTrue(Validator.isMobile(username)
                || Validator.isEmail(username), "用户账号格式不正确,请输入手机号/邮箱");
        UserVo vo = new UserVo().setCity(userVo.getCity())
                .setJob(userVo.getJob()).setSignature(userVo.getSignature()).setSex(userVo.getSex())
                .setUid(IdUtils.get16UUID())
                .setNickname(userVo.getNickname()).setRoleId(userVo.getRoleId());
        if (StringUtils.isNotBlank(userVo.getAvatar())) {
            vo.setAvatar(userVo.getAvatar());
        } else {
            vo.setAvatar(CommonConstant.DEFAULT_AVATAR);
        }
        String mobile = userVo.getMobile();
        String email = userVo.getEmail();
        if (StringUtils.isBlank(userVo.getNickname())) {
            vo.setNickname("用户" + System.currentTimeMillis());
        }
        Integer roleId = userVo.getRoleId();
        Assert.notNull(roleId, "角色编号不能为空");
        Role role = roleService.getById(roleId);
        Assert.notNull(role, "该角色不存在");
        vo.setRoleId(roleId);

        // 更新
        if (Objects.nonNull(userVo.getId())) {
            vo.setId(userVo.getId());
            Assert.isTrue(Validator.isMobile(mobile), "请输入正确的手机号");
            Assert.isTrue(Validator.isEmail(email), "请输入正确的邮箱");
            User byId = userService.getById(userVo.getId());
            Assert.notNull(byId, "用户不存在");
            if (!Objects.equals(byId.getUsername(), username)) {
                User otherUser = userService.findUserNotContain(byId.getId(), username);
                Assert.isNull(otherUser, "该账号已被其他用户占用");
                vo.setUsername(username);
            }
            if (StringUtils.isNotBlank(mobile)
                    && !Objects.equals(byId.getMobile(), mobile)) {
                User otherUser = userService.findUserNotContain(byId.getId(), mobile);
                Assert.isNull(otherUser, "该手机号已被其他用户占用");
                vo.setMobile(mobile);
            }
            if (StringUtils.isNotBlank(email)
                    && !Objects.equals(byId.getEmail(), email)) {
                User otherUser = userService.findUserNotContain(byId.getId(), email);
                Assert.isNull(otherUser, "该邮箱已被其他用户占用");
                vo.setEmail(email);
            }

            return vo;
        }
        String password = userVo.getPassword();
        String checkPassword = userVo.getCheckPassword();
        Assert.equals(password, checkPassword, "两次密码输入不一致");
        vo.setPassword(passwordEncoder.encode(password));

        User byUsername = userService.findByUsername(username);
        Assert.isNull(byUsername, "该账号已被其他用户占用");
        vo.setUsername(username);
        if (StringUtils.isNotBlank(mobile)) {
            Assert.isTrue(Validator.isMobile(mobile), "请输入正确的手机号");
            User userMobile = userService.findByUsername(mobile);
            Assert.isNull(userMobile, "该手机号已被其他用户占用");
            vo.setMobile(mobile);
        } else if (Validator.isMobile(username)) {
            vo.setMobile(username);
        }
        if (StringUtils.isNotBlank(email)) {
            Assert.isTrue(Validator.isEmail(email), "请输入正确的邮箱");
            User userEmail = userService.findByUsername(mobile);
            Assert.isNull(userEmail, "该邮箱已被其他用户占用");
            vo.setMobile(email);
        } else if (Validator.isEmail(username)) {
            vo.setEmail(username);
        }
        return vo;
    }

    @PostMapping("/create")
    public Response userCreate(@RequestBody UserVo userVo) {
        UserVo checkParams = checkParams(userVo);
        User user = mapperFacade.map(checkParams, User.class);
        user.setUpdatedBy(SecurityUtils.getUserId()).setUpdatedAt(LocalDateTime.now())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId());
        UserRole userRole = new UserRole().setUserId(checkParams.getId()).setRoleId(checkParams.getRoleId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId());
        userComponent.createUser(user, userRole);
        return ResponseHelper.createSuccess();
    }

    @PostMapping("/teacher-update")
    public Response updateAccountInfo(@RequestBody Teachers teachers) {
        Integer teacherId = teachers.getId();
        Assert.notNull(teacherId, "讲师编号不能为空");
        String name = StringUtils.trim(teachers.getName());
        Assert.notBlank(name, "讲师名称不能为空");
        Teachers byUserId = teachersService.getById(teacherId);
        Assert.notNull(byUserId, "讲师信息不存在");
        LambdaQueryWrapper<Teachers> query = Wrappers.lambdaQuery();
        query.eq(Teachers::getName, name)
                .ne(Teachers::getId, byUserId.getId());
        int count = teachersService.count(query);
        Assert.equals(0, count, "该讲师名称已被占用");
        Teachers updateTeacher = new Teachers().setId(byUserId.getId()).setName(name)
                .setJob(teachers.getJob())
                .setIntroduction(teachers.getIntroduction())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        if (StringUtils.isNotBlank(teachers.getAvatar())) {
            updateTeacher.setAvatar(teachers.getAvatar());
        }
        teachersService.updateById(updateTeacher);
        return ResponseHelper.updateSuccess();
    }
}
