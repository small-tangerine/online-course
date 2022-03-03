package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @date 2020-05-11
 * @Description: 性别枚举
 */
@AllArgsConstructor
public enum SexEnum {
    /**
     * 用户性别枚举
     */
    MALE("male","男"),
    FEMALE("female", "女"),
    UN_KNOW("unknown", "保密");

    public static String getDescFromSex(String sex) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (Objects.equals(sexEnum.getSex(), sex)) {
                return sexEnum.getText();
            }
        }
        return "";
    }


    @Getter
    private final String sex;
    @Getter
    private final String text;

    public static SexEnum fromDesc(String sexText) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (StringUtils.equals(sexEnum.getText(), sexText)) {
                return sexEnum;
            }
        }
        return null;
    }
}
