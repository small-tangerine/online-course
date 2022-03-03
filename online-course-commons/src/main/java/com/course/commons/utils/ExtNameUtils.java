package com.course.commons.utils;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @date 2020-11-07
 * @Description: 扩展名测试
 */
public class ExtNameUtils {

    /**
     * 类Unix路径分隔符
     */
    private static final char UNIX_SEPARATOR = CharUtil.SLASH;
    /**
     * Windows路径分隔符
     */
    private static final char WINDOWS_SEPARATOR = CharUtil.BACKSLASH;
    /**
     * 额外的文件扩展名
     */
    private static final List<String> EXTEND_EXT_NAMES = Lists.newArrayList(".tar.gz");

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名，不带"."
     */
    public static String extName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(StrUtil.DOT);
        if (index == -1) {
            return StrUtil.EMPTY;
        } else {
            String extendExtName = getExtendExtName(fileName);
            if (StrUtil.isNotBlank(extendExtName)) {
                return extendExtName;
            }
            String ext = fileName.substring(index + 1);
            // 扩展名中不能包含路径相关的符号
            return StrUtil.containsAny(ext, UNIX_SEPARATOR, WINDOWS_SEPARATOR) ? StrUtil.EMPTY : ext;
        }
    }

    /**
     * 是否有额外的扩展名
     *
     * @param fileName 文件名
     * @return 额外的扩展名
     */
    private static String getExtendExtName(String fileName) {

        for (String extendExtName : EXTEND_EXT_NAMES) {
            if (fileName.endsWith(extendExtName)) {
                return StrUtil.sub(extendExtName, 1, extendExtName.length());
            }
        }
        return null;
    }
}
