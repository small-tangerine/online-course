package com.course.commons.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description: 字符串工具类
 */
public class StringUtil {
    /**
     * 字符串截取默认长度
     */
    private final static int DEFAULT_LENGTH = 14;

    /**
     * 驼峰匹配
     */
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 下划线匹配
     */
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 邮箱正则表达式匹配
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");

    /**
     * 修饰截取字符串，如果截取了，后面加上"..."
     * 使用默认长度
     *
     * @param str 字符串
     * @return 修饰截取的字符串
     */
    public static String wrapSubstring(String str) {
        return wrapSubstring(str, DEFAULT_LENGTH);
    }

    /**
     * 修饰截取字符串，如果截取了，后面加上"..."
     * 使用默认长度
     *
     * @param str    字符串
     * @param length 需要截取字符串的长度
     * @return 修饰截取的字符串
     */
    public static String wrapSubstring(String str, int length) {
        if (Objects.isNull(str)) {
            return null;
        }
        String substring = StringUtils.substring(str, 0, length);
        if (str.length() > length) {
            substring += "...";
        }
        return substring;
    }

    /**
     * 判断是否是下划线模式，直接判断是否有大写字母
     *
     * @param str 字符串
     * @return true or false
     */
    public static boolean isHump(String str) {
        //驼峰模式
        Matcher humpMatcher = HUMP_PATTERN.matcher(str);
        //下划线匹配
        Matcher lineMatcher = LINE_PATTERN.matcher(str);
        return !lineMatcher.find() && humpMatcher.find();
    }

    /**
     * 下划线转驼峰
     * user_name  ---->  userName
     * house.user_name  ---->  userName
     * userName   --->  userName
     *
     * @param underlineName 带有下划线的名字
     * @return 驼峰字符串
     */
    public static String underlineToHump(String underlineName) {
        //截取下划线分成数组
        char[] charArray = underlineName.toCharArray();
        //判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, l = charArray.length; i < l; i++) {
            //判断当前字符是否是"_",如果跳出本次循环
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                //如果为true，代表上次的字符是"_",当前字符需要转成大写
                sb.append(charArray[i] -= 32);
                underlineBefore = false;
            } else { //不是"_"后的字符就直接追加
                sb.append(charArray[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰转 下划线
     * userName  ---->  user_name
     * user_name  ---->  user_name
     *
     * @param humpName 驼峰命名
     * @return 带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        //截取下划线分成数组，
        char[] charArray = humpName.toCharArray();
        StringBuilder sb = new StringBuilder();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                sb.append("_").append(charArray[i] += 32);
            } else {
                sb.append(charArray[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 从使用英文逗号分隔的id字符串中得到id集合
     *
     * @param idStr id集合字符串，使用英文逗号分隔
     * @return id集合
     */
    public static List<Integer> getIdList(String idStr) {
        if (StringUtils.isBlank(idStr)) {
            return null;
        }
        return Arrays.stream(idStr.split(","))
                .filter(StringUtils::isNotBlank)
                .filter(StringUtils::isNumeric)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * 判断字符串是否是由字母数字组成
     *
     * @param cs 字符串
     * @return true  or false
     */
    public static boolean isAlphanumeric(final CharSequence cs) {
        if (StringUtils.isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!CharUtils.isAsciiAlphanumeric(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmail(String emailText) {
        if (StringUtils.isBlank(emailText)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(emailText).find();
    }

    /**
     * 从使用特定分隔符分隔的id字符串中得到id集合
     *
     * @param idStr id集合字符串
     * @param regex 分隔符
     * @return id集合
     */
    public static List<String> getIdList(String idStr, String regex) {
        if (StringUtils.isBlank(idStr)) {
            return Collections.emptyList();
        }
        // "1,2"
        List<String> collect = Arrays.stream(idStr.split(regex))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        // "1"
        if (CollectionUtils.isEmpty(collect)) {
            return ImmutableList.of(StringUtils.trim(idStr));
        }
        return collect;
    }

}
