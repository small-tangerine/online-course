package com.course.commons.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.course.commons.enums.TimePatternEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**

 * @date 2019-12-16
 * @Description: 时间工具类
 */
public class TimeUtils {

    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 按格式得到星期几
     * dayOfWeek.getDisplayName(TextStyle.FULL, defaultLocal));     // 星期二
     * dayOfWeek.getDisplayName(TextStyle.SHORT, defaultLocal));    // 星期二
     * dayOfWeek.getDisplayName(TextStyle.NARROW, defaultLocal));   // 二
     *
     * @param dayOfWeek 星期几
     * @param textStyle 格式
     * @return 需要格式的字符串
     */
    public static String getDayOfWeek(DayOfWeek dayOfWeek, TextStyle textStyle) {
        if (Objects.isNull(textStyle)) {
            textStyle = TextStyle.NARROW;
        }
        ///这段代码在有的服务器中会显示英文的
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        return dayOfWeek.getDisplayName(textStyle, locale);
    }

    /**
     * 获取数字星期几
     *
     * @param dayOfWeek 星期几
     * @return number
     */
    public static int getDayOfWeekValue(DayOfWeek dayOfWeek) {
        if (Objects.isNull(dayOfWeek)) {
            return 0;
        }
        return dayOfWeek.getValue();
    }

    public static Date toDate(String dateStr, String... paramsPatterns) {
        LocalDateTime localDateTime = toLocalDateTime(dateStr, paramsPatterns);
        return localDateTime2Date(localDateTime);
    }

    public static LocalDateTime toLocalDateTime(String localDateTimeStr, String... paramsPatterns) {
        DateTimeFormatter dateTimeFormatter;
        if (paramsPatterns != null && paramsPatterns.length > 0) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(paramsPatterns[0]);
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(TimePatternEnum.DATE_TIME.pattern());
        }
        return LocalDateTime.parse(localDateTimeStr, dateTimeFormatter);
    }

    public static LocalDate toLocalDate(Integer dateInteger) {
        Assert.notNull(dateInteger, "dateInteger不能为空");
        return toLocalDate(String.valueOf(dateInteger), "yyyyMMdd");
    }

    public static LocalDate toLocalDate(String localDateStr, String... paramsPatterns) {
        if (StringUtils.isBlank(localDateStr)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter;
        if (paramsPatterns != null && paramsPatterns.length > 0) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(paramsPatterns[0]);
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(TimePatternEnum.DATE.pattern());
        }
        return LocalDate.parse(localDateStr, dateTimeFormatter);
    }

    /**
     * 使用月份
     *
     * @param yearMonthStr 月份字符串，ex：2021-01
     * @param pattern      匹配模式字符串，ex：yyyy-MM
     * @return 月份
     */
    public static YearMonth toYearMonth(String yearMonthStr, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return YearMonth.parse(yearMonthStr, dateTimeFormatter);
    }

    /**
     * 得到当前几天后23:59的date；比如两天后的23:59:59
     *
     * @param days 天数
     * @return 日期
     */
    public static Date getDateAfterDaysEnd(long days) {
        LocalDate localDate = LocalDate.now().plusDays(days);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(23, 59, 59));
        return localDateTime2Date(localDateTime);
    }

    public static String format(Date date, String... parsePatterns) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return format(localDateTime, parsePatterns);
    }


    public static String format(LocalDate localDate, String... paramPatterns) {
        if (Objects.isNull(localDate)) {
            return "";
        }
        String pattern;
        if (paramPatterns != null && paramPatterns.length > 0) {
            pattern = paramPatterns[0];
        } else {
            pattern = TimePatternEnum.DATE.pattern();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateTimeFormatter);
    }

    public static String format(LocalTime localTime, String... paramPatterns) {
        if (Objects.isNull(localTime)) {
            return "";
        }
        String pattern;
        if (ArrayUtil.isNotEmpty(paramPatterns)) {
            pattern = paramPatterns[0];
        } else {
            pattern = "HH:mm:ss";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localTime.format(dateTimeFormatter);
    }

    /**
     * 格式化时间
     */
    public static String format(LocalDateTime localDateTime, String... paramPatterns) {
        String pattern = null;

        if (paramPatterns != null && paramPatterns.length > 0) {
            pattern = paramPatterns[0];
        } else {
            pattern = TimePatternEnum.DATE_TIME.pattern();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(localDateTime);
    }

    public static String format(YearMonth yearMonth, String... paramPatterns) {
        String pattern = null;
        if (ArrayUtils.isNotEmpty(paramPatterns)) {
            pattern = paramPatterns[0];
        } else {
            pattern = "yyyy-MM";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return yearMonth.format(dtf);
    }

    public static String format(Year year, String... paramPatterns) {
        String pattern = null;
        if (ArrayUtils.isNotEmpty(paramPatterns)) {
            pattern = paramPatterns[0];
        } else {
            pattern = "yyyy";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return year.format(dtf);
    }

    public static String formatChinese(LocalDate localDate) {
        Assert.notNull(localDate, "localDate不能为空");
        if (Objects.equals(LocalDate.now().getYear(), localDate.getYear())) {
            return localDate.format(DateTimeFormatter.ofPattern("MM月dd日"));
        } else {
            return localDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        }
    }

    public static String formatChinese(YearMonth yearMonth) {
        Assert.notNull(yearMonth, "yearMonth不能为空");
        return format(yearMonth, "yyyy年MM月");
    }

    /**
     * Date转LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        //atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime instant2LocalDateTime(Instant instant) {
        ZoneId zoneId = ZoneId.systemDefault();
        //atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Integer localDateTime2EpochSecond(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        long epochSecond = localDateTime.toEpochSecond(getZoneOffset());
        return (int) epochSecond;
    }

    public static LocalDateTime epochSecond2LocalDateTime(Integer epochSecond) {
        if (Objects.isNull(epochSecond)) {
            return null;
        }
        ZoneOffset currentOffsetForMyZone = getZoneOffset();

        return LocalDateTime.ofEpochSecond(epochSecond, 0, currentOffsetForMyZone);
    }

    /**
     * 获得时区
     */
    private static ZoneOffset getZoneOffset() {
        //can be LocalDateTime
        Instant instant = Instant.now();
        // my timezone
        ZoneId systemZone = ZoneId.systemDefault();
        return systemZone.getRules().getOffset(instant);
    }

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * localDate转Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * localDate转Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        //设置时区
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 得到两个时间戳的天数差
     *
     * @param start 开始时间，理论上更小
     * @param end   结束时间，理论上更大
     * @return 相差的天数
     */
    public static long between(LocalDateTime start, LocalDateTime end) {
        Assert.notNull(start, "start must not be null");
        Assert.notNull(end, "end must not be null");
        return Duration.between(start, end).toDays();
    }

    /**
     * 计算天数相差的天数，整整的天数，只是一个例子
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 天数
     */
    public static long differDays(LocalDate start, LocalDate end) {
        Assert.notNull(start, "start must not be null");
        Assert.notNull(end, "end must not be null");
        return start.until(end, ChronoUnit.DAYS);
    }

    /**
     * 计算相差的月份,理论上结束时间比开始时间大
     *
     * @param endMonth   结束时间
     * @param startMonth 开始时间
     * @return 大减小月份
     */
    public static int differMonth(YearMonth endMonth, YearMonth startMonth) {
        return endMonth.compareTo(startMonth);
    }

    /**
     * 得到两个日期之间的天数差，
     * 年月日中的日差，先计算年，再计算月，再计算日（也就说日不会超过30天）
     *
     * @param start 开始时间，理论上更小
     * @param end   结束时间，理论上更大
     * @return 相差的天数
     * @deprecated 请使用differDays
     */
    @Deprecated
    public static int between(LocalDate start, LocalDate end) {
        Assert.notNull(start, "start must not be null");
        Assert.notNull(end, "end must not be null");
        return Period.between(start, end).getDays();
    }

    /**
     * 得到一天的开始时间
     *
     * @param date 日期
     * @return 日期时间
     */
    public static LocalDateTime getDateStart(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    /**
     * 得到一天的最大时间
     *
     * @param date 日期
     * @return 日期时间
     */
    public static LocalDateTime getDateEnd(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDateTime.of(date, LocalTime.MAX);
    }

    /**
     * 简单的得到时间，如果是今年，是 xx月xx日，如果不是，xxxx年xx月xx日
     *
     * @param dateTime 时间
     * @return 字符串
     */
    public static String formatSimpleYear(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return "";
        }
        if (Objects.equals(dateTime.getYear(), Year.now().getValue())) {
            return format(dateTime, "MM月dd日 HH:mm");
        }
        return format(dateTime, "yyyy年MM月dd日 HH:mm");
    }

    public static String formatSimpleYear(LocalDate date) {
        if (Objects.isNull(date)) {
            return "";
        }
        if (Objects.equals(date.getYear(), Year.now().getValue())) {
            return format(date, "MM月dd日");
        }
        return format(date, "yyyy年MM月dd日");
    }

    /**
     * 得到当前月第一周的第一天
     *
     * @param yearMonth 年-月
     * @return 第一周第一天的日期
     */
    public static LocalDate getFirstMonthWeekDay(YearMonth yearMonth) {
        return TimeUtils.date2LocalDate(DateUtil.beginOfWeek(TimeUtils.localDate2Date(LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1))));
    }

    /**
     * 得到月中的周的数量
     *
     * @param yearMonth 年-月
     * @return 穿过来的月的周数
     */
    public static int getMonthWeekNumber(YearMonth yearMonth) {
        return DateUtil.weekOfMonth(TimeUtils.localDate2Date(LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), yearMonth.lengthOfMonth())));
    }

    /**
     * 得到当前月最后一周的最后一天
     *
     * @param yearMonth 年-月
     * @return 最后一天最后一天的日期
     */
    public static LocalDate getLastMonthWeekDay(YearMonth yearMonth) {
        LocalDate firstMonthWeekDay = getFirstMonthWeekDay(yearMonth);
        int monthWeekNumber = getMonthWeekNumber(yearMonth);
        return firstMonthWeekDay.plusDays(7 * monthWeekNumber - 1);
    }

    /**
     * 第一项大于等于第二项
     *
     * @param first  第一项
     * @param second 第二项
     * @return 第一项是否大于第二项
     */
    public static boolean greaterThanAndEqual(LocalDateTime first, LocalDateTime second) {
        return first.isAfter(second) || first.equals(second);
    }

    /**
     * 第一项小于等于第二项
     *
     * @param first  第一项
     * @param second 第二项
     * @return 第一项是否小于等于第二项
     */
    public static boolean lessThanAndEqual(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second) || first.equals(second);
    }

    public static boolean lessThanAndEqual(LocalDate first, LocalDate second) {
        return first.isBefore(second) || first.equals(second);
    }

    public static boolean greaterThanAndEqual(LocalDate first, LocalDate second) {
        return first.isAfter(second) || first.equals(second);
    }

    public static boolean greaterThanAndEqual(YearMonth first, YearMonth second) {
        return first.isAfter(second) || first.equals(second);
    }

    /**
     * 渲染成数据库认识的年月
     *
     * @param yearMonth 年月
     * @return integer
     */
    public static Integer formatYearMonthInteger(YearMonth yearMonth) {
        return Integer.valueOf(format(yearMonth, "yyyyMM"));
    }

    /**
     * 将数据库中的年月数据渲染成年月
     *
     * @param yearMonth integer
     * @return 年月
     */
    public static YearMonth toYearMonth(Integer yearMonth) {
        return toYearMonth(String.valueOf(yearMonth), "yyyyMM");
    }

    public static YearMonth date2YearMonth(Date date) {
        LocalDate localDate = TimeUtils.date2LocalDate(date);
        return YearMonth.of(localDate.getYear(), localDate.getMonth());
    }


    /**
     * 获取当前日期所在的周一到周日的所有日期集合
     *
     * @return ["mm.dd"]
     */
    public static List<String> getWeekDateList() {
        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();

        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();

        List<Date> lDate = new ArrayList<>();
        lDate.add(mondayDate);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(mondayDate);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(sundayDate);
        //测试此日期是否在指定日期之后
        while (sundayDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate.stream().map(item -> {
            Calendar calendar = Calendar.getInstance();
            Date nowTime = calendar.getTime();
            String time = format(item, "MM.dd");
            String now = format(nowTime, "MM.dd");
            if (now.equals(time)) {
                return "今天";
            }
            return time;
        }).collect(Collectors.toList());
    }

    /**
     * 获取上周一
     *
     * @param now 当前时间
     * @return 上周一时间开始
     */
    public static LocalDateTime getLastWeekStartDateTime(LocalDateTime now) {
        // 当前星期几 1...7
        int dayOfWeek = getDayOfWeekValue(now.getDayOfWeek());
        LocalDateTime lastMonday = now.minusDays(dayOfWeek + 6);
        return getDateStart(lastMonday.toLocalDate());
    }

    /**
     * 获取指定日期的上周一
     *
     * @param date 指定日期
     * @return 上周一日期
     */
    public static LocalDate getLastWeekStartDate(LocalDate date) {
        int dayOfWeek = getDayOfWeekValue(date.getDayOfWeek());
        return date.minusDays(dayOfWeek + 6);
    }

    /**
     * 获取指定日期的周一
     *
     * @param date 指定日期
     * @return 周一日期
     */
    public static LocalDate getThisWeekStartDate(LocalDate date) {
        int dayOfWeek = getDayOfWeekValue(date.getDayOfWeek());
        if (dayOfWeek == 1) {
            return date;
        }
        return date.minusDays(dayOfWeek - 1);
    }

    /**
     * 获取指定日期的周日
     *
     * @param date 指定日期
     * @return 周日日期
     */
    public static LocalDate getThisWeekEndDate(LocalDate date) {
        int dayOfWeek = getDayOfWeekValue(date.getDayOfWeek());
        if (dayOfWeek == 7) {
            return date;
        }
        return date.plusDays(7 - dayOfWeek);
    }

    /**
     * 获取上周日结束时间
     *
     * @param now 当前时间
     * @return 上周日时间
     */
    public static LocalDateTime getLastWeekEndDateTime(LocalDateTime now) {
        // 当前星期几 1...7
        int dayOfWeek = getDayOfWeekValue(now.getDayOfWeek());
        LocalDateTime lastSunday = now.minusDays(dayOfWeek);
        return getDateEnd(lastSunday.toLocalDate());
    }

    /**
     * 获取指定日期的上周日
     *
     * @param date 指定看日期
     * @return 上周日日期
     */
    public static LocalDate getLastWeekEndDate(LocalDate date) {
        int dayOfWeekValue = getDayOfWeekValue(date.getDayOfWeek());
        return date.minusDays(dayOfWeekValue);
    }

    /**
     * 获得指定日期是所在月份的第几周<br>
     *
     * @param date 日期
     * @return 周
     */
    public static int weekOfMonth(LocalDate date) {
        return DateTime.of(TimeUtils.localDate2Date(date)).weekOfMonth();
    }

    /**
     * 通过日期获得int 值
     *
     * @param date 日期
     * @return int值 yyyyMMdd
     */
    public static Integer intValue(LocalDate date) {
        Assert.notNull(date, "date不能为空");
        return Integer.valueOf(TimeUtils.format(date, "yyyyMMdd"));
    }

    public static Integer intValue(YearMonth yearMonth) {
        Assert.notNull(yearMonth, "yearMonth不能为空");
        return Integer.valueOf(TimeUtils.format(yearMonth, "yyyyMM"));
    }


    public static Integer formatYearInteger(Year year) {
        return Integer.valueOf(format(year, "yyyy"));
    }

    public static Integer formatLocalDateInteger(LocalDate localdate) {
        return Integer.valueOf(format(localdate, "yyyyMMdd"));
    }

    /**
     * 第一项要大于第二项
     *
     * @param first
     * @param second
     * @return
     */
    public static Long calculateHour(LocalDateTime first, LocalDateTime second) {
        Duration duration = Duration.between(second, first);
        return duration.toHours();
    }

    /**
     * 当前时间时间戳
     * @param localDateTime 当前时间
     * @return 时间戳
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }
}
