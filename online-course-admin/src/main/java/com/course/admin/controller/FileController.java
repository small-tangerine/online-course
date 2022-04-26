package com.course.admin.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
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

/**
 * 头像
 *
 * @since 2022-03-03
 */
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class FileController {

    private static final String BASE_URL = "http://localhost/";
    private final UserService userService;
    private final RoleService roleService;
    private final TeachersService teachersService;

    /**
     * 图片上传
     *
     * @param image 图片
     * @return response
     */
    @PostMapping("/image-upload")
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

            return Response.ok("图片上传成功", ImmutableMap.of("url", avatar));
        } catch (IOException e) {
            log.error("图片上传失败", e);
            return Response.fail("图片上传失败");
        }
    }

}
