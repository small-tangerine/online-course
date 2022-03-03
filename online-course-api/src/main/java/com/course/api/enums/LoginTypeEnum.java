package com.course.api.enums;

import com.course.commons.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
public enum LoginTypeEnum {
    FRONT(1, "用户端"),
    BACKEND(2, "管理端");

    @Getter
    private final Integer typeId;
    @Getter
    private final String typeDesc;

    public static String getDescFromSexType(Integer typeId) {
        for (LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()) {
            if (Objects.equals(loginTypeEnum.getTypeId(), typeId)) {
                return loginTypeEnum.getTypeDesc();
            }
        }
        return "";
    }
}
