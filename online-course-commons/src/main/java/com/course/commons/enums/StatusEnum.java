package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @date 2019-12-17
 * @Description: status枚举
 */
@AllArgsConstructor
public enum StatusEnum {
    /**
     * status枚举
     */
    WAIT(0, "待审核"),
    SUCCESS(1, "审核通过"),
    FAIL(2,"审核不通过");

    public static StatusEnum fromStatus(Integer status) {
        for (StatusEnum value : StatusEnum.values()) {
            if (Objects.equals(value.status, status)) {
                return value;
            }
        }
        return null;
    }

    @Getter
    private final Integer status;
    @Getter
    private final String desc;

    public static Integer getStandardStatus(Integer status) {
        StatusEnum statusEnum = fromStatus(status);
        if (Objects.nonNull(statusEnum)) {
            return statusEnum.status;
        }
        return null;
    }

    public static String getDescFromStatus(Integer status) {
        StatusEnum statusEnum = fromStatus(status);
        if (Objects.nonNull(statusEnum)) {
            return statusEnum.desc;
        }
        return "";
    }
    public static boolean containsStatus(Integer typeId) {
        for (StatusEnum typeEnum : StatusEnum.values()) {
            if (Objects.equals(typeEnum.getStatus(), typeId)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断status是否等于当前
     * @return true：等于
     */
    public boolean equalsStatus(Integer status) {
        return this.status.equals(status);
    }
}
