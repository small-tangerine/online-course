package com.course.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @date 2020-05-12
 * @Description: 权限类型枚举
 */
@AllArgsConstructor
public enum PermissionTypeEnum {
    /**
     * 权限类型枚举
     */
    MENU(1, "顶部菜单"),
    PAGE(2, "页面"),
    BUTTON(3, "按钮");

    @Getter
    private final Integer type;
    private final String desc;

    public boolean equalsType(Integer type) {
        return this.type.equals(type);
    }

}
