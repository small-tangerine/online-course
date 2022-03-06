package com.course.server.controller;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.User;
import com.course.api.enums.LoginTypeEnum;
import com.course.api.vo.server.UserVo;
import com.course.commons.annotations.AccountInfo;
import com.course.commons.annotations.BaseInfo;
import com.course.commons.enums.SexEnum;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.server.config.security.SecurityUtils;
import com.course.api.entity.UserToken;
import com.course.service.service.UserService;
import com.course.service.service.UserTokenService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
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
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserInfoController {

    private final UserService userService;
    private final MapperFacade mapperFacade;
    private final PasswordEncoder passwordEncoder;
    private final UserTokenService userTokenService;

    @PostMapping("/update-base-info")
    public Response updateBaseInfo(@RequestBody @Validated(BaseInfo.class) UserVo userVo) {
        Assert.notBlank(SexEnum.getDescFromSex(userVo.getSex()), "请选择正确的性别");
        Integer userId = SecurityUtils.getUserId();
        User user = userService.getById(userId);
        User updateBaseInfo = new User().setId(userId)
                .setNickname(userVo.getNickname())
                .setJob(userVo.getJob())
                .setCity(userVo.getCity())
                .setSignature(userVo.getSignature())
                .setUpdatedBy(userId)
                .setUpdatedAt(LocalDateTime.now())
                .setSex(userVo.getSex());
        userService.updateById(updateBaseInfo);
        user.setNickname(userVo.getNickname())
                .setJob(userVo.getJob())
                .setCity(userVo.getCity())
                .setSignature(userVo.getSignature())
                .setSex(userVo.getSex())
                .setPassword(null);
        return ResponseHelper.updateSuccess(mapperFacade.map(user, UserVo.class));
    }

    @PostMapping("/update-account-info")
    public Response updateAccountInfo(@RequestBody @Validated(AccountInfo.class) UserVo userVo) {
        Assert.isTrue(Validator.isMobile(userVo.getMobile()), "请输入正确的手机号码");
        Integer userId = SecurityUtils.getUserId();
        User user = userService.getById(userId);
        String username = SecurityUtils.getUsername();

        // 校验手机、邮箱是否已经绑定其他账号6
        checkMobileAndEmail(userVo.getMobile(), userVo.getEmail(), user);
        if (!Objects.equals(username, user.getUsername())) {
            UserToken userToken = userTokenService.findByUserIdAndType(userId, LoginTypeEnum.FRONT.getTypeId());
            if (Objects.nonNull(userToken)) {
                userTokenService.removeById(userToken.getUserId());
            }
            // 更新了账号重新登录
            return ResponseHelper.updateSuccess(ImmutableMap.of("isUpdateUsername",
                    YesOrNoEnum.YES.getValue()));
        }

        User updateBaseInfo = new User().setId(userId)
                .setMobile(userVo.getMobile())
                .setEmail(userVo.getEmail())
                .setUpdatedBy(userId)
                .setUpdatedAt(LocalDateTime.now());

        // 更新密码
        boolean isUpdate = false;
        if (StringUtils.isNotBlank(userVo.getPassword())) {
            Assert.notBlank(userVo.getCheckPassword(), "请输入确认密码");
            Assert.isFalse(passwordEncoder.matches(userVo.getPassword(), user.getPassword()),
                    "新密码不能与旧密码相同");
            Assert.equals(userVo.getCheckPassword(), userVo.getPassword(), "两次密码输入不一致");
            updateBaseInfo.setPassword(passwordEncoder.encode(userVo.getPassword()));
            isUpdate = true;
        }

        userService.updateById(updateBaseInfo);

        // 更新了密码同样重新登录
        if (isUpdate) {
            UserToken userToken = userTokenService.findByUserIdAndType(userId, LoginTypeEnum.FRONT.getTypeId());
            if (Objects.nonNull(userToken)) {
                userTokenService.removeById(userToken.getUserId());
            }
            // 更新了账号重新登录
            return ResponseHelper.updateSuccess(ImmutableMap.of("isUpdateUsername",
                    YesOrNoEnum.YES.getValue()));
        }
        user.setEmail(userVo.getEmail())
                .setMobile(userVo.getMobile())
                .setPassword(null);

        return ResponseHelper.updateSuccess(mapperFacade.map(user, UserVo.class));
    }

    private void checkMobileAndEmail(String mobile, String email, User user) {
        // 如果邮箱、手机号没修改直接return
        if (Objects.equals(mobile, user.getMobile()) && Objects.equals(email, user.getEmail())) {
            return;
        }
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.ne(User::getId, user.getId())
                .and(item -> item.eq(User::getEmail, email).or().eq(User::getMobile, mobile))
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
            user.setEmail(email);
        }
        user.setMobile(mobile).setEmail(email);
    }
}
