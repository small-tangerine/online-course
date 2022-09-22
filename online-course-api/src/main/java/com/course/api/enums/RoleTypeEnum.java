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
    TEACHER(2, "讲师"),
    ADMIN(1, "管理员"),
    STUDENT(3, "学生");

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

    public static boolean containsStatus(Integer typeId) {
        for (RoleTypeEnum typeEnum : RoleTypeEnum.values()) {
            if (Objects.equals(typeEnum.getType(), typeId)) {
                return true;
            }
        }
        return false;
    }
}
