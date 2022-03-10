package com.course.admin.controller;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.vo.LoginVo;
import com.course.api.vo.admin.UserVo;
import com.course.commons.annotations.AccountInfo;
import com.course.commons.enums.SexEnum;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.service.service.TeachersService;
import com.course.service.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 用户信息
 *
 * @author panguangming
 * @since 2022-03-03
 */
@RequestMapping("/user-info")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserInfoController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TeachersService teachersService;

    @PostMapping("/update-base-info")
    public Response updateBaseInfo(@RequestBody UserVo userVo) {
        Assert.notBlank(SexEnum.getDescFromSex(userVo.getSex()), "请选择正确的性别");
        Assert.notBlank(userVo.getNickname(), "用户昵称不能为空");
        Integer userId = SecurityUtils.getUserId();
        User updateBaseInfo = new User().setId(userId)
                .setNickname(userVo.getNickname())
                .setUpdatedBy(userId)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId)
                .setSex(userVo.getSex());
        userService.updateById(updateBaseInfo);
        return ResponseHelper.updateSuccess();
    }

    @PostMapping("/update-teacher-info")
    public Response updateAccountInfo(@RequestBody Teachers teachers) {
        Integer userId = SecurityUtils.getUserId();
        String name = StringUtils.trim(teachers.getName());
        Assert.notBlank(name, "讲师名称不能为空");
        Teachers byUserId = teachersService.getByUserId(userId);
        Assert.notNull(byUserId, "讲师信息不存在");
        LambdaQueryWrapper<Teachers> query = Wrappers.lambdaQuery();
        query.eq(Teachers::getName, name)
                .ne(Teachers::getId,byUserId.getId());
        int count = teachersService.count(query);
        Assert.equals(0, count, "该讲师名称已被占用");
        Teachers updateTeacher = new Teachers().setId(byUserId.getId()).setName(name)
                .setJob(teachers.getJob())
                .setIntroduction(teachers.getIntroduction())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId);
        teachersService.updateById(updateTeacher);
        return ResponseHelper.updateSuccess();
    }

    /**
     * 更新手机号
     *
     * @param userVo 实体
     * @return response
     */
    @PostMapping("/update-phone")
    public Response accountUpdatePhone(@RequestBody LoginVo userVo) {
        Integer userId = SecurityUtils.getUserId();
        String mobile = userVo.getMobile();
        String verifyCode = userVo.getVerifyCode();
        String password = userVo.getPassword();
        String username = SecurityUtils.getUsername();
        Assert.isTrue(StringUtils.isNotBlank(mobile)
                && Validator.isMobile(mobile), "手机号格式不正确");
        Assert.notBlank(verifyCode, "验证码不能为空");
        // 获取用户信息
        User byId = userService.getById(userId);
        Assert.isTrue(passwordEncoder.matches(password, byId.getPassword()), "密码不正确");
        checkMobileAndEmail(mobile, null, byId);
        User updatePhone = new User().setId(userId)
                .setUsername(byId.getUsername())
                .setMobile(mobile)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId);
        userService.updateById(updatePhone);
        return ResponseHelper.updateSuccess(ImmutableMap.of("isUpdateUsername",
                !Objects.equals(username, updatePhone.getUsername())));
    }

    /**
     * 更新邮箱
     *
     * @param userVo 实体
     * @return response
     */
    @PostMapping("/update-email")
    public Response accountUpdateEmail(@RequestBody LoginVo userVo) {
        Integer userId = SecurityUtils.getUserId();
        String email = userVo.getEmail();
        String verifyCode = userVo.getVerifyCode();
        String password = userVo.getPassword();
        String username = SecurityUtils.getUsername();
        Assert.isTrue(StringUtils.isNotBlank(email)
                && Validator.isEmail(email), "邮箱格式不正确");
        Assert.notBlank(verifyCode, "验证码不能为空");
        // 获取用户信息
        User byId = userService.getById(userId);
        Assert.isTrue(passwordEncoder.matches(password, byId.getPassword()), "密码不正确");
        checkMobileAndEmail(null, email, byId);
        User updateEmail = new User().setId(userId)
                .setUsername(byId.getUsername())
                .setEmail(email)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId);
        userService.updateById(updateEmail);
        return ResponseHelper.updateSuccess(ImmutableMap.of("isUpdateUsername",
                !Objects.equals(username, updateEmail.getUsername())));
    }

    @PostMapping("update-password")
    public Response accountUpdatePassword(@RequestBody @Validated(AccountInfo.class) UserVo userVo) {
        String password = userVo.getPassword();
        String checkPassword = userVo.getCheckPassword();
        String oldPassword = userVo.getOldPassword();
        Integer userId = SecurityUtils.getUserId();
        User byId = userService.getById(userId);
        Assert.notBlank(oldPassword, "旧密码不能为空");
        Assert.notBlank(checkPassword, "确认密码不能为空");
        Assert.isTrue(passwordEncoder.matches(oldPassword, byId.getPassword()), "旧密码不正确");
        Assert.equals(password, checkPassword, "两次密码不一致");
        String newPassword = passwordEncoder.encode(password);
        User updatePassword = new User().setId(userId).setPassword(newPassword)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId);
        userService.updateById(updatePassword);
        return ResponseHelper.updateSuccess();
    }

    private void checkMobileAndEmail(String mobile, String email, User user) {
        // 如果邮箱、手机号没修改直接return
        if (Objects.equals(mobile, user.getMobile()) && StringUtils.isBlank(email)) {
            return;
        }
        if (StringUtils.isBlank(mobile) && Objects.equals(email, user.getEmail())) {
            return;
        }
        if (Objects.equals(mobile, user.getMobile()) && Objects.equals(email, user.getEmail())) {
            return;
        }
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.ne(User::getId, user.getId())
                .and(item -> item.eq(StringUtils.isNotBlank(email), User::getEmail, email)
                        .or().eq(StringUtils.isNotBlank(mobile), User::getMobile, mobile))
                .select(User::getId, User::getEmail, User::getMobile, User::getUsername);
        List<User> list = userService.list(query);
        StringBuilder error = new StringBuilder();
        list.forEach(item -> {
            if (Objects.equals(item.getEmail(), email)) {
                error.append("该邮箱已被绑定");
            }
            if (Objects.equals(item.getMobile(), mobile)) {
                error.append("该手机号已被绑定");
            }
        });
        Assert.isTrue(StringUtils.isBlank(error), error.toString());
        if (Validator.isMobile(user.getUsername())) {
            user.setUsername(mobile);
        }
        if (Validator.isEmail(user.getUsername())) {
            user.setUsername(email);
        }
        if (StringUtils.isNotBlank(email)) {
            user.setEmail(email);
        }
        if (StringUtils.isNotBlank(mobile)) {
            user.setMobile(mobile);
        }
    }
}
