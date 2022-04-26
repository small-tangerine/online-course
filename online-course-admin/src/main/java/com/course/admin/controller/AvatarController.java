package com.course.admin.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.Role;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.enums.RoleTypeEnum;
import com.course.commons.constant.CommonConstant;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.FileUtils;
import com.course.service.service.RoleService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 头像
 *
 * @since 2022-03-03
 */
@RequestMapping("/avatar")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AvatarController {

    private static final String BASE_URL = "http://localhost/";
    private final UserService userService;
    private final RoleService roleService;
    private final TeachersService teachersService;

    /**
     * 上传头像
     *
     * @param image 图片
     * @return response
     */
    @PostMapping("/upload")
    public Response upload(@RequestParam("image") MultipartFile image) {
        Assert.notNull(image, "没有图片");

        String originalFilename = image.getOriginalFilename();
        String suffix = FileUtil.getSuffix(originalFilename);
        Assert.notBlank(suffix, "未知文件类型");
        Assert.isTrue(CommonConstant.IMG_SUFFIX.contains(suffix), "该文件不是图片");

        try {
            InputStream inputStream = image.getInputStream();
            // 文件路径
            String filePath = FileUtils.saveImg(inputStream, originalFilename);
            Path path = Paths.get(CommonConstant.FILE_PREFIX, filePath);
            File destFile = path.toFile();
            String type = FileTypeUtil.getType(destFile);
            Assert.isTrue(CommonConstant.IMG_SUFFIX.contains(type), "指定文件不是图片");
            String avatar = BASE_URL + filePath;

            Integer userId = SecurityUtils.getUserId();
            Role userRole = roleService.findUserRole(userId);
            // 讲师头像信息
            if (RoleTypeEnum.TEACHER.equalsStatus(userRole.getId())) {
                Teachers teacher = teachersService.getByUserId(userId);
                Teachers updateTeacher = new Teachers().setAvatar(avatar)
                        .setUserId(userId)
                        .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId);
                if (Objects.nonNull(teacher)) {
                    updateTeacher.setId(teacher.getId());
                }
                teachersService.saveOrUpdate(updateTeacher);
                return Response.ok("上传头像成功", ImmutableMap.of("url", avatar));
            }
            // 更新用户头像信息
            User updateAvatar = new User().setId(SecurityUtils.getUserId())
                    .setAvatar(avatar)
                    .setUpdatedAt(LocalDateTime.now())
                    .setUpdatedBy(SecurityUtils.getUserId());
            userService.updateById(updateAvatar);
            return Response.ok("上传头像成功", ImmutableMap.of("url", avatar));
        } catch (IOException e) {
            log.error("上传头像失败", e);
            return Response.fail("上传头像失败");
        }
    }

    /**
     * 上传头像
     *
     * @param image 图片
     * @return response
     */
    @PostMapping("/user-upload")
    public Response Userupload(@RequestParam("image") MultipartFile image) {
        Assert.notNull(image, "没有图片");

        String originalFilename = image.getOriginalFilename();
        String suffix = FileUtil.getSuffix(originalFilename);
        Assert.notBlank(suffix, "未知文件类型");
        Assert.isTrue(CommonConstant.IMG_SUFFIX.contains(suffix), "该文件不是图片");

        try {
            InputStream inputStream = image.getInputStream();
            // 文件路径
            String filePath = FileUtils.saveImg(inputStream, originalFilename);
            Path path = Paths.get(CommonConstant.FILE_PREFIX, filePath);
            File destFile = path.toFile();
            String type = FileTypeUtil.getType(destFile);
            Assert.isTrue(CommonConstant.IMG_SUFFIX.contains(type), "指定文件不是图片");
            String avatar = BASE_URL + filePath;
            return Response.ok("上传头像成功", ImmutableMap.of("url", avatar));
        } catch (IOException e) {
            log.error("上传头像失败", e);
            return Response.fail("上传头像失败");
        }
    }
}
