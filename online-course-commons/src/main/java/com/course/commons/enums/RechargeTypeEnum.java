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
public enum RechargeTypeEnum {
    /**
     * 用户性别枚举
     */
    IN(1, "充值"),
    OUT(0, "消费");

    public static String getDescFromType(Integer type) {
        for (RechargeTypeEnum payTypeEnum : RechargeTypeEnum.values()) {
            if (Objects.equals(payTypeEnum.getType(), type)) {
                return payTypeEnum.getTypeTitle();
            }
        }
        return "";
    }


    @Getter
    private final Integer type;
    @Getter
    private final String typeTitle;

    public static RechargeTypeEnum fromTypeTitle(String typeTitle) {
        for (RechargeTypeEnum payTypeEnum : RechargeTypeEnum.values()) {
            if (StringUtils.equals(payTypeEnum.getTypeTitle(), typeTitle)) {
                return payTypeEnum;
            }
        }
        return null;
    }
    public boolean equalsType(Integer type) {
        return this.type.equals(type);
    }
}
