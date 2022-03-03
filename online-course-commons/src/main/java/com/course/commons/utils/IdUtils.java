package com.course.commons.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Date: 2020-01-07
 * @Description: Id工具类
 */
@Slf4j
public class IdUtils {

    private final static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成随机数字
     * 参数System.currentTimeMillis():这是java里面的获取1970年到目前的毫秒数,是一个13位数的数字，与Date.getTime()函数的结果一样，
     * 比如1378049585093。经过研究，在2013年，前三位是137，在2023年是168，到2033年才199.所以，我决定第一位数字1可以去掉，不要占位置了。
     * 随机1000字符串+指定区分位
     *
     * @param typeId 对应类型
     * @return 随机字符串
     */
    public static String randomNumber(Integer typeId) {
        int random = ThreadLocalRandom.current().nextInt(1000);
        return String.valueOf(System.currentTimeMillis()).substring(1) + String.format("%03d", random) + ObjectUtil.defaultIfNull(typeId, 0);
    }

    /**
     * 生成订单id
     * 以下为网上摘抄：其实最好的还是雪花算法，但现在还不成熟
     * String orderId =
     * machineId +
     * (System.currentTimeMillis() + "").substring(1) +
     * (System.nanoTime() + "").substring(7, 10);
     * 参数machineId：是集群时的机器代码，可以1-9任意。部署时，分别为部署的项目手动修改该值，以确保集群的多台机器在系统时间上不一致的问题（毫无疑问每台机器的毫秒数基本上不一致）。
     * 参数System.currentTimeMillis():这是java里面的获取1970年到目前的毫秒数,是一个13位数的数字，与Date.getTime()函数的结果一样，比如1378049585093。经过研究，在2013年，前三位是137，在2023年是168，到2033年才199.所以，我决定第一位数字1可以去掉，不要占位置了。可以肯定绝大多数系统用不了10年20年。这样，参数2就变成了12位数的数字，加上参数1machineId才13位数。
     * 参数System.nanoTime()：这是java里面的取纳秒数，经过深入研究，在同一毫秒内，位置7,8,9这三个数字是会变化的。所以决定截取这三个数字出来拼接成一个16位数的订单号。
     * 总结：理论上此方案在同一秒内，可以应对1000*1000个订单号，但是经过测试，在每秒并发2000的时候，还是会出现2-10个重复。
     *
     * @return 订单id
     */
    public static String orderId() {
        String day = TimeUtils.format(LocalDate.now(), "yyyyMMdd");

        return day + String.valueOf(System.currentTimeMillis()).substring(1) + String.valueOf(System.nanoTime()).substring(7, 10);
    }

    /**
     * 生成订单id
     * 以下为网上摘抄：其实最好的还是雪花算法，但现在还不成熟
     * String orderId =
     * machineId +
     * (System.currentTimeMillis() + "").substring(1) +
     * (System.nanoTime() + "").substring(7, 10);
     * 参数machineId：是集群时的机器代码，可以1-9任意。部署时，分别为部署的项目手动修改该值，以确保集群的多台机器在系统时间上不一致的问题（毫无疑问每台机器的毫秒数基本上不一致）。
     * 参数System.currentTimeMillis():这是java里面的获取1970年到目前的毫秒数,是一个13位数的数字，与Date.getTime()函数的结果一样，比如1378049585093。经过研究，在2013年，前三位是137，在2023年是168，到2033年才199.所以，我决定第一位数字1可以去掉，不要占位置了。可以肯定绝大多数系统用不了10年20年。这样，参数2就变成了12位数的数字，加上参数1machineId才13位数。
     * 参数System.nanoTime()：这是java里面的取纳秒数，经过深入研究，在同一毫秒内，位置7,8,9这三个数字是会变化的。所以决定截取这三个数字出来拼接成一个16位数的订单号。
     * 总结：理论上此方案在同一秒内，可以应对1000*1000个订单号，但是经过测试，在每秒并发2000的时候，还是会出现2-10个重复。
     *
     * @return 订单id
     */
    public static String orderSn() {
        String day = TimeUtils.format(LocalDate.now(), "MMdd");

        return day + String.valueOf(System.currentTimeMillis()).substring(1) + String.valueOf(System.nanoTime()).substring(7, 10);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成8位数的UUID
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符，
     *
     * @return 短8位的UUID
     */
    public static String generateShortUuid() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            // 0x3E 62
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 生成Token
     *
     * @return token
     */
    public static String makeToken() {
        try {
            MessageDigest sha = MessageDigest.getInstance("sha");
            sha.update(String.valueOf(System.currentTimeMillis()).getBytes());

            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(UUID.randomUUID().toString().getBytes());

            return byteArrayToHex(md5.digest()) + "-" + byteArrayToHex(sha.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error("生成算法错误", e);
            return String.valueOf(UUID.randomUUID());
        }
    }


    private static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
    public static String get16UUID(){
        // 2.中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = TimeUtils.format(LocalDateTime.now(),"HHmmss");
        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 4.可能为负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        return dayTime + String.format("%010d", hashCode);
    }

}
