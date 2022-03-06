package com.course.server.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.course.api.entity.User;
import com.course.api.vo.server.UserVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.FileUtils;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
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

/**
 * 头像
 *
 * @author panguangming
 * @since 2022-03-03
 */
@RequestMapping("/avatar")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AvatarController {

    private static final String BASE_URL = "http://localhost/avatar/";
    private final UserService userService;
    private final MapperFacade mapperFacade;

    /**
     * 上传头像
     *
     * @param image 图片
     * @return response
     */
    @PostMapping("/upload")
    public Response upload( @RequestParam("image") MultipartFile image) {
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
            User user = userService.getById(SecurityUtils.getUserId());
            User updateAvatar = new User().setId(SecurityUtils.getUserId()).setAvatar(avatar)
                    .setUpdatedAt(LocalDateTime.now())
                    .setUpdatedBy(SecurityUtils.getUserId());
            userService.updateById(updateAvatar);
            user.setAvatar(avatar).setPassword(null);
            return Response.ok("上传头像成功", mapperFacade.map(user, UserVo.class));
        } catch (IOException e) {
            log.error("上传头像失败", e);
            return Response.fail("上传头像失败");
        }
    }
}
