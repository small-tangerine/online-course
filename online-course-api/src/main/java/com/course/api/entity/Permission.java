package com.course.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级编号
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @TableField("title")
    private String title;

    /**
     * 菜单类型 1目录 2菜单 3按钮
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 菜单排序
     */
    @TableField("display_order")
    private Integer displayOrder;

    /**
     * 前端路由
     */
    @TableField("path")
    private String path;

    /**
     * 路由主键地址
     */
    @TableField("component")
    private String component;

    /**
     * 路由重定向地址
     */
    @TableField("redirect")
    private String redirect;

    /**
     * 菜单英文名称
     */
    @TableField("en_title")
    private String enTitle;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 是否隐藏 1是 0否
     */
    @TableField("is_hidden")
    private Integer isHidden;

    /**
     * 权限标志
     */
    @TableField("permission_tag")
    private String permissionTag;

    /**
     * 菜单描述
     */
    @TableField("note")
    private String note;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 创建人ID
     */
    @TableField("created_by")
    private Integer createdBy;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    /**
     * 更新人ID
     */
    @TableField("updated_by")
    private Integer updatedBy;


}
