package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @date 2020-05-11
 * @Description: 支付类型枚举
 */
@AllArgsConstructor
public enum PayTypeEnum {
    /**
     * 用户性别枚举
     */
    BALANCE(1, "余额", "余额支付"),
    ALI_PAY(2, "支付宝", "支付宝支付"),
    WE_CHAT(3, "微信", "微信支付");

    public static String getDescFromType(Integer type) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (Objects.equals(payTypeEnum.getType(), type)) {
                return payTypeEnum.getTypeTitle();
            }
        }
        return "";
    }

    public static String getNameFromType(Integer type) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (Objects.equals(payTypeEnum.getType(), type)) {
                return payTypeEnum.getName();
            }
        }
        return "";
    }

    @Getter
    private final Integer type;
    @Getter
    private final String name;
    @Getter
    private final String typeTitle;

    public static PayTypeEnum fromTypeTitle(String typeTitle) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (StringUtils.equals(payTypeEnum.getTypeTitle(), typeTitle)) {
                return payTypeEnum;
            }
        }
        return null;
    }
}
