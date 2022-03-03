package com.course.commons.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.course.commons.constant.CommonConstant;
import com.course.commons.enums.TimePatternEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @date 2020-01-05
 * @Description: 文件保存
 */
@Slf4j
public class FileUtils {
    /**
     * 视频文件的后缀名
     */
    private static final Set<String> VIDEO_FORMAT_SETS = CommonConstant.VIDEO_SUFFIX;

    /**
     * 保存本地文件的前缀
     */
    private static final String FILE_PREFIX = CommonConstant.FILE_PREFIX;

    /**
     * 存储文件
     *
     * @param bytes            字节
     * @param originalFilename 原文件名
     * @return 保存后的文件名
     * @throws IOException 异常
     */
    public static String saveFile(byte[] bytes, String originalFilename) throws IOException {
        String suffix = getFileSuffix(originalFilename);
        String urlPrefix = "file/" + TimeUtils.format(LocalDate.now(), TimePatternEnum.DATE_NO_LINE.pattern());
        String fileName = generateFileName() + StrUtil.DOT + suffix;
        return saveFile(bytes, urlPrefix, fileName);
    }

    public static String saveFile(InputStream inputStream, String originalFilename) throws IOException {
        String suffix = getFileSuffix(originalFilename);
        String urlPrefix = "file/" + TimeUtils.format(LocalDate.now(), TimePatternEnum.DATE_NO_LINE.pattern());
        String fileName = generateFileName() + StrUtil.DOT + suffix;
        return saveFile(inputStream, urlPrefix, fileName);
    }

    /**
     * 存储视频文件
     *
     * @param videoBytes 视频文件数组
     * @return response
     */
    public static String saveVideo(byte[] videoBytes, String originalFilename) throws IOException {
        String suffix = getFileSuffix(originalFilename, "video");
        String urlPrefix = "video/" + TimeUtils.format(LocalDate.now(), TimePatternEnum.DATE_NO_LINE.pattern());
        String videoName = generateFileName() + StrUtil.DOT + suffix;
        return saveFile(videoBytes, urlPrefix, videoName);
    }

    public static String saveVideo(InputStream inputStream, String originalFilename) throws IOException {
        String suffix = getFileSuffix(originalFilename, "video");
        String urlPrefix = "video/" + TimeUtils.format(LocalDate.now(), TimePatternEnum.DATE_NO_LINE.pattern());
        String videoName = generateFileName() + StrUtil.DOT + suffix;
        return saveFile(inputStream, urlPrefix, videoName);
    }

    /**
     * 存储图片
     *
     * @param imgBytes         文件字节数组
     * @param originalFilename 原来的文件名，得到文件后缀
     * @return 需要保存的图片url
     * @throws IOException 可能IOException
     */
    public static String saveImg(byte[] imgBytes, String originalFilename) throws IOException {
        String suffix = getFileSuffix(originalFilename, "img");
        String urlPrefix = "image/" + TimeUtils.format(LocalDate.now(), TimePatternEnum.DATE_NO_LINE.pattern());
        String fileName = generateFileName() + StrUtil.DOT + suffix;
        return saveFile(imgBytes, urlPrefix, fileName);
    }

    /**
     * 存储图片
     *
     * @param inputStream      输入流
     * @param originalFilename 原来的文件名，得到文件后缀
     * @return 需要保存的图片url
     * @throws IOException 可能IOException
     */
    public static String saveImg(InputStream inputStream, String originalFilename) throws IOException {
        String suffix = getFileSuffix(originalFilename, "img");
        String urlPrefix = "image/" + TimeUtils.format(LocalDate.now(), TimePatternEnum.DATE_NO_LINE.pattern());
        String fileName = generateFileName() + suffix;
        return saveFile(inputStream, urlPrefix, fileName);
    }

    /**
     * 获取文件后缀，并检查
     *
     * @param originalFilename 原文件名
     * @return 文件后缀
     */
    private static String getFileSuffix(String originalFilename, String imgOrVideo) {
        String originalFormat = getFileSuffix(originalFilename);
        checkSuffix(originalFilename, originalFormat, imgOrVideo);
        return "."+originalFormat;
    }

    /**
     * 获取文件后缀
     *
     * @param originalFilename 源文件名
     * @return 文件后缀
     */
    public static String getFileSuffix(String originalFilename) {
        Assert.notNull(originalFilename, "没有原文件名");
        String extNameNotContainDot = ExtNameUtils.extName(originalFilename);
        Assert.isTrue(StringUtils.isNotBlank(extNameNotContainDot), "文件名错误：【" + originalFilename + "】");
        return extNameNotContainDot;
    }

    private static void checkSuffix(String originalFilename, String originalFormat, String imgOrVideo) {
        switch (imgOrVideo) {
            case "img":
                Set<String> imgFormatSets = CommonConstant.IMG_SUFFIX;
                Assert.isTrue(imgFormatSets.contains(originalFormat),
                        String.format("不支持该文件的存储【文件名：%s】，只支持【%s】的后缀名", originalFilename,
                                StringUtils.join(imgFormatSets, ",")));
                break;
            case "video":
                Set<String> videoFormatSets = CommonConstant.VIDEO_SUFFIX;
                Assert.isTrue(videoFormatSets.contains(originalFormat),
                        String.format("不支持该文件的存储【文件名：%s】，只支持【%s】的后缀名", originalFilename,
                                StringUtils.join(VIDEO_FORMAT_SETS, ",")));
                break;
            default:
        }

    }

    /**
     * 保存文件到本地
     *
     * @param bytes      字节数组
     * @param folderPath 文件夹路径
     * @param fileName   文件名
     * @return 保存到数据库的文件名
     * @throws IOException IOException
     */
    private static String saveFile(byte[] bytes, String folderPath, String fileName) throws IOException {

        mkdirParent(folderPath);
        Path path = Paths.get(FILE_PREFIX, folderPath, fileName);
        Files.write(path, bytes);
        return folderPath + "/" + fileName;
    }

    private static String saveFile(InputStream inputStream, String folderPath, String fileName) throws IOException {

        mkdirParent(folderPath);
        Path path = Paths.get(FILE_PREFIX, folderPath, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path.toFile());
            IoUtil.copy(inputStream, fos);
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(fos);
        }
        return folderPath + "/" + fileName;
    }

    private static void mkdirParent(String folderPath) {
        File filePath = new File(FILE_PREFIX, folderPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
    }

    /**
     * 生成文件名
     *
     * @return 文件名
     */
    public static String generateFileName() {
        int random = ThreadLocalRandom.current().nextInt(10000);
        return String.valueOf(System.currentTimeMillis()) + random;
    }

}
