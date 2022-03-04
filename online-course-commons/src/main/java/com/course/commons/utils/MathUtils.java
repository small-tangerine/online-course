package com.course.commons.utils;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @date 2019-12-23
 * @Description: 数学运算
 */
@Slf4j
public class MathUtils {

    /**
     * 去掉多余的0
     *
     * @param bigDecimal bi
     * @return 字符串
     */
    public static String getPrettyNumberStr(BigDecimal bigDecimal) {
        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    public static BigDecimal div(int dividend, int divisor, int scale) {
        return div(BigDecimal.valueOf(dividend), BigDecimal.valueOf(divisor), scale, RoundingMode.HALF_UP);
    }

    /**
     * 除于
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    保留几位小数
     * @return BigDecimal
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor, int scale) {
        // 渲染除于0操作
        if (equalsObj(BigDecimal.ZERO, divisor)) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, scale, RoundingMode.HALF_UP);
    }

    /**
     * 除于
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    保留几位小数
     * @param round    是四舍五入还是怎么样，
     * @return BigDecimal
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode round) {
        if (equalsObj(BigDecimal.ZERO, divisor)) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, scale, round);
    }

    /**
     * 生成指定范围的多个随机数
     *
     * @param number 需要生成随机数的数量
     * @param start  需要生成随机数的开始范围，包含
     * @param end    需要生成随机数的结束范围，包含
     * @return 结果
     */
    public static Collection<Integer> generateRandoms(Integer number, int start, int end) {
        // 范围
        int range = end - start + 1;
        Assert.isTrue(range >= number, "参数错误");
        int limit = 20;
        if (end - start - number < limit) {
            if (log.isDebugEnabled()) {
                log.debug("生成的数量：{}，开始索引：{}，结束索引：{}，使用数组减少的方法", number, start, end);
            }
            List<Integer> list = Lists.newArrayListWithCapacity(range);
            // 需要生成的数组与范围小于20个
            for (int i = start; i <= end; i++) {
                list.add(i);
            }
            // 打乱顺序
            Collections.shuffle(list);
            // 防止错误
            int rangeCopy = range;
            while (list.size() > number) {
                int random = ThreadLocalRandom.current().nextInt(0, rangeCopy);
                list.remove(random);
                // 范围需要减1，在范围内减1
                rangeCopy--;
            }
            return list;
        } else {
            int count = 0;
            // 使用set去重
            Set<Integer> set = Sets.newLinkedHashSetWithExpectedSize(number);
            while (set.size() < number) {
                int random = ThreadLocalRandom.current().nextInt(start, end);
                set.add(random);
                if (log.isDebugEnabled()) {
                    count++;
                }
            }
            if (log.isDebugEnabled()) {
                log.debug("生成的数量：{}，开始索引：{}，结束索引：{}，使用Set去重的方法,随机生成 {} 次", number, start, end, count);
            }
            return set;
        }
    }

    /**
     * 如果第一项小于第二项，则返回负数
     *
     * @param min 小项
     * @param max 大象
     * @return 如果第一项小于第二项，返回负数，如果大于，则是正数
     */
    public static int compareTo(BigDecimal min, BigDecimal max) {
        return min.compareTo(max);
    }

    /**
     * 第一项是否大于等于第二项
     *
     * @param first  第一项
     * @param second 第二项
     * @return true or false
     */
    public static boolean greaterThanOrEqual(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) >= 0;
    }

    /**
     * 第一项是否大于第二项
     *
     * @param first  第一项
     * @param second 第二项
     * @return true or false
     */
    public static boolean greaterThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) > 0;
    }

    /**
     * 第一项是否小于第二项
     *
     * @param first  第一项
     * @param second 第二项
     * @return true or false
     */
    public static boolean lessThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) < 0;
    }

    public static boolean equalsObj(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) == 0;
    }

    /**
     * 第一项是否小于登录第二项
     *
     * @param first  第一项
     * @param second 第二项
     * @return true or false
     */
    public static boolean lessThanOrEqual(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) <= 0;
    }

    /**
     * 70%返回70
     *
     * @param rateText 当前字符串
     * @return 转换成费率
     */
    public static BigDecimal coverRateBigDecimal(String rateText) {
        if (StrUtil.isBlank(rateText) || !rateText.endsWith("%")) {
            return null;
        }
        String subStr = StrUtil.subBefore(rateText, '%', true);
        try {
            return new BigDecimal(subStr);
        } catch (Exception e) {
            return null;
        }
    }
}
