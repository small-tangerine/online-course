package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**

 * @date 2019-12-17
 * @Description: 是或者不是解决
 */
@AllArgsConstructor
public enum YesOrNoEnum {
    /**
     * 是或者不是
     */
    NO(0, "否"),
    YES(1, "是");

    @Getter
    private final Integer value;
    @Getter
    private final String desc;

    /**
     * 通过结果返回必要的值
     *
     * @param bool 表达式
     * @return 0或者1
     */
    public static Integer getResult(Boolean bool) {
        return Boolean.TRUE.equals(bool) ? YES.getValue() : NO.getValue();
    }

    public static Integer getResult(Integer value) {
        return Objects.equals(1, value) ? YES.getValue() : NO.getValue();
    }

    public static Integer getYesOrNo(Integer value) {
        YesOrNoEnum yesOrNoEnum = fromInteger(value);
        if (Objects.nonNull(yesOrNoEnum)) {
            return yesOrNoEnum.getValue();
        }
        return null;
    }

    public static boolean coverToBool(Integer value) {
        return Objects.equals(YES.getValue(), value);
    }

    public static String getDescFromValue(Integer isVideo) {
        return coverToBool(isVideo) ? "是" : "否";
    }

    public static Integer getReverseResult(Integer value) {
        return Objects.equals(1, value) ? NO.getValue() : YES.getValue();
    }

    public static Integer getReverseResult(boolean bool) {
        return bool ? NO.getValue() : YES.getValue();
    }

    public static YesOrNoEnum fromDesc(String desc) {
        for (YesOrNoEnum value : YesOrNoEnum.values()) {
            if (StringUtils.equals(value.getDesc(), desc)) {
                return value;
            }
        }
        return null;
    }

    public String getValueString() {
        return String.valueOf(this.value);
    }

    public static YesOrNoEnum fromInteger(Integer value) {
        for (YesOrNoEnum yesOrNoEnum : YesOrNoEnum.values()) {
            if (Objects.equals(value, yesOrNoEnum.value)) {
                return yesOrNoEnum;
            }
        }
        return null;
    }

    public boolean equalsStatus(Integer status) {
        return this.value.equals(status);
    }
}
