package com.course.commons.constant;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * @date 2020-05-11
 * @description 平时要用的常量
 */
public class CommonConstant {
    private CommonConstant() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 设备类型key
     */
    public static final String DEVICE = "Device";

    /**
     * 视频文件中的后缀名
     */
    public static final Set<String> VIDEO_SUFFIX = Sets.newHashSet("mov", "mp4", "m4a", "3gp", "3g2", "mj2", "flv");

    /**
     * 图片后缀
     */
    public static final Set<String> IMG_SUFFIX = Sets.newHashSet("jpg", "jpeg", "png", "bmp");

    /**
     * 阿里云可转码并在线预览的文件后缀
     */
    public static final Set<String> FILE_SUFFIX = Sets.newHashSet(
            //演示文件
            "pptx", "ppt", "pot", "potx", "pps", "ppsx", "dps", "dpt", "pptm", "potm", "ppsm",
            //表格文件
            "xls", "xlt", "et", "ett", "xlsx", "xltx", "csv", "xlsb", "xlsm", "xltm",
            //文字文件
            "doc", "dot", "wps", "wpt", "docx", "dotx", "docm", "dotm",
            //其他格式文件
            "pdf", "lrc", "c", "cpp", "h", "asm", "s", "java", "asp", "bat", "bas", "prg", "cmd", "rtf", "txt", "log", "xml", "htm", "html"
    );

    /**
     * 微信可预览的文件后缀
     */
    public static final Set<String> WX_FILE_SUFFIX = Sets.newHashSet(
            //演示文件
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf"
    );

    /**
     * 保存在本地中的前缀
     */
    public static final String FILE_PREFIX = "/data/upload/";
    /**
     * 文件暂存列表
     */
    public static final String FILE_TEMP_PREFIX = FILE_PREFIX + "temp";


    /**
     * 手机号校验的正则
     */
    public static final Pattern MOBILE_COMPILE = Pattern.compile("^1([3456789])\\d{9}$");

    /**
     * 超级管理员权限
     */
    public static final Integer ADMIN_ROLE_ID = 1;


    /**
     * 已删除用户默认显示值
     */
    public static final String DELETE_USER_TITLE = "已删除用户";

    public static final String DELETE_ADMIN_TITLE = "已删除管理员";


    /**
     * 允许上传的文件类型
     */
    public static final Set<String> ALLOW_UPLOAD_FILE_TYPES = Sets.newHashSet(
            "gif", "png", "jpg", "jpeg",
            "txt", "xml", "csv",
            "pdf", "doc", "pptx", "ppt", "xls", "docx", "xlsx",
            "mp4", "m4v", "wvm", "mov", "avi", "flv",
            "zip", "rar", "gz", "7z", "bmp"
    );

    /**
     * 阿里云可转码并在线预览的文件后缀
     */
    public static final Set<String> PREVIEW_FILE_SUFFIX = Sets.newHashSet(
            // 表格文件
            "et", "xls", "xlt", "xlsx", "xlsm", "xltx", "xltm", "csv",
            // 文字文件
            "doc", "docx", "txt", "dot", "wps", "wpt", "dotx", "docm", "dotm", "rtf",
            // 演示文件
            "ppt", "pptx", "pptm", "ppsx", "ppsm", "pps", "potx", "potm", "dpt", "dps",
            // pdf文件
            "pdf"
    );

}
