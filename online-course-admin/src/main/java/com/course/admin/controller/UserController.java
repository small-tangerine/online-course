package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.Role;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.entity.UserRole;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.admin.UserVo;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
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
}
