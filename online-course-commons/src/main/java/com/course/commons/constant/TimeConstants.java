package com.course.commons.constant;

/**
 * 时间默认格式
 *
 * @since 2022-01-25
 */
public class TimeConstants {

    private TimeConstants(){
        throw new IllegalStateException("Utility class");
    }
    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

}
