package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhongjiahua
 * @date 2019-12-16
 * @Description: 时间模板工具类
 */
@AllArgsConstructor
public enum TimePatternEnum {
    //"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss","HH:mm","MM月dd日","yyyy-MM-dd HH:mm:ss.SSS"
    DATE("yyyy-MM-dd"),
    /**
     * 从字符串解析到LocalDateTime会报错，请注意
     */
    YEAR_MONTH("yyyy-MM"),
    DATE_TIME("yyyy-MM-dd HH:mm:ss"),
    /**
     * 到分钟
     */
    DATE_MINUTE("yyyy-MM-dd HH:mm"),
    MINUTES("HH:mm"),
    MONTH_DAY_WITH_CHINESE("MM月dd日"),
    DATE_NO_LINE("yyyyMMdd"),
    DATE_TIME_NO_LINE_TO_MINUTE("yyyyMMddHHmm"),
    DATE_TIME_MILLISECOND("yyyy-MM-dd HH:mm:ss.SSS");

    @Getter
    private final String pattern;

    public String pattern() {
        return this.pattern;
    }
}
