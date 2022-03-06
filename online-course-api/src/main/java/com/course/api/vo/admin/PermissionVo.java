package com.course.api.vo.admin;

import com.course.commons.enums.YesOrNoEnum;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 菜单权限
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class PermissionVo {

    /**
     * 菜单编号
     */
    private Integer id;

    /**
     * 父级编号
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单类型 1目录 2菜单 3按钮
     */
    private Integer typeId;

    /**
     * 菜单排序
     */
    private Integer displayOrder;

    /**
     * 前端路由
     */
    private String path;

    /**
     * 路由主键地址
     */
    private String component;

    /**
     * 路由重定向地址
     */
    private String redirect;

    /**
     * 菜单英文名称
     */
    private String enTitle;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否隐藏 1是 0否
     */
    private Integer isHidden;

    /**
     * 权限标志
     */
    private String permissionTag;

    /**
     * 菜单描述
     */
    private String note;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    private Map<String, Object> meta;

    private List<PermissionVo> children;

    private Boolean hidden;

    public PermissionVo setHidden() {
        this.hidden = YesOrNoEnum.YES.equalsStatus(this.isHidden);
        return this;
    }

    public PermissionVo setMeta() {
        Map<String, Object> metaMap = Maps.newHashMapWithExpectedSize(2);
        if (Objects.nonNull(this.typeId) && !Objects.equals(3, this.typeId)) {
            metaMap.put("title", this.enTitle);
            metaMap.put("icon", this.icon);
            this.meta = metaMap;
        }
        return this;
    }
}
