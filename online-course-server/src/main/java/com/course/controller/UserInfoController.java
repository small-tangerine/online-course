package com.course.controller;

import com.course.api.entity.User;
import com.course.api.vo.server.UserVo;
import com.course.commons.enums.SexEnum;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.config.security.SecurityUtils;
import com.course.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

    @PostMapping("/update-base-info")
    public Response updateBaseInfo(@RequestBody UserVo userVo) {
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
                .setSignature(userVo.getSignature());
        return ResponseHelper.updateSuccess(mapperFacade.map(user, UserVo.class));
    }

    @PostMapping("/update-account-info")
    public Response updateAccountInfo() {
        User user = userService.getById(SecurityUtils.getUserId());
        return ResponseHelper.updateSuccess(mapperFacade.map(user, UserVo.class));
    }
}
