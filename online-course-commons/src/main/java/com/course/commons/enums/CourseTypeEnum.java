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
public enum CourseTypeEnum {
    /**
     * 是或者不是
     */
    FREE(1, "免费","免费课程"),
    UN_FREE(2, "付费","实战课程");

    @Getter
    private final Integer value;
    @Getter
    private final String desc;
    @Getter
    private final String name;


    public static CourseTypeEnum fromDesc(String desc) {
        for (CourseTypeEnum value : CourseTypeEnum.values()) {
            if (StringUtils.equals(value.getDesc(), desc)) {
                return value;
            }
        }
        return null;
    }

    public String getValueString() {
        return String.valueOf(this.value);
    }

    public static CourseTypeEnum fromInteger(Integer value) {
        for (CourseTypeEnum yesOrNoEnum : CourseTypeEnum.values()) {
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
