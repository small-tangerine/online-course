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
public enum RecommendTypeEnum {
    /**
     * 是或者不是
     */
    RECOMMEND(1, "实战推荐"),
    NEW(2, "新上好课");

    @Getter
    private final Integer type;
    @Getter
    private final String title;


    public static RecommendTypeEnum fromDesc(String title) {
        for (RecommendTypeEnum type : RecommendTypeEnum.values()) {
            if (StringUtils.equals(type.getTitle(), title)) {
                return type;
            }
        }
        return null;
    }

    public String getValueString() {
        return String.valueOf(this.type);
    }

    public static RecommendTypeEnum fromInteger(Integer type) {
        for (RecommendTypeEnum yesOrNoEnum : RecommendTypeEnum.values()) {
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
        for (RecommendTypeEnum typeEnum : RecommendTypeEnum.values()) {
            if (Objects.equals(typeEnum.getType(), typeId)) {
                return true;
            }
        }
        return false;
    }
}
