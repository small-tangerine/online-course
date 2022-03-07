package com.course.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**

 * @date 2019-12-17
 * @Description: 是或者不是解决
 */
@AllArgsConstructor
public enum RoleTypeEnum {
    /**
     * 是或者不是
     */
    SUPER_ADMIN(1, "超级管理员"),
    ADMIN(2, "管理员"),
    TEACHER(3, "讲师"),
    STUDENT(4, "学生");

    @Getter
    private final Integer type;
    @Getter
    private final String title;


    public static RoleTypeEnum fromDesc(String title) {
        for (RoleTypeEnum type : RoleTypeEnum.values()) {
            if (StringUtils.equals(type.getTitle(), title)) {
                return type;
            }
        }
        return null;
    }

    public String getValueString() {
        return String.valueOf(this.type);
    }

    public static RoleTypeEnum fromInteger(Integer type) {
        for (RoleTypeEnum yesOrNoEnum : RoleTypeEnum.values()) {
            if (Objects.equals(type, yesOrNoEnum.type)) {
                return yesOrNoEnum;
            }
        }
        return null;
    }

    public boolean equalsStatus(Integer status) {
        return this.type.equals(status);
    }
}
