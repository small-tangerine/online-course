package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @date 2020-05-11
 * @Description: 支付状态枚举
 */
@AllArgsConstructor
public enum PayStatusEnum {
    /**
     * 支付状态枚举
     */
    UN_PAY(0, "未支付"),
    PAY(1, "已完成"),
    CANCEL(2, "已取消");

    public static String getDescFromStatus(Integer status) {
        for (PayStatusEnum payStatusEnum : PayStatusEnum.values()) {
            if (Objects.equals(payStatusEnum.getStatus(), status)) {
                return payStatusEnum.getStatusTitle();
            }
        }
        return "";
    }


    @Getter
    private final Integer status;
    @Getter
    private final String statusTitle;

    public static PayStatusEnum fromDesc(String statusTitle) {
        for (PayStatusEnum payStatusEnum : PayStatusEnum.values()) {
            if (StringUtils.equals(payStatusEnum.getStatusTitle(), statusTitle)) {
                return payStatusEnum;
            }
        }
        return null;
    }

    public boolean equalsStatus(Integer status) {
        return this.status.equals(status);
    }
}
