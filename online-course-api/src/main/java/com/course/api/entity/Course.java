package com.course.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 背景图
     */
    @TableField("bg_img")
    private String bgImg;

    /**
     * 封面图
     */
    @TableField("banner")
    private String banner;

    /**
     * 课程须知
     */
    @TableField("tip")
    private String tip;

    /**
     * 能学到什么
     */
    @TableField("learn_what")
    private String learnWhat;

    /**
     * 课程类型 1免费 2付费
     */
    @TableField("type")
    private Integer type;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 是否折扣 1是 -否
     */
    @TableField("is_discount")
    private Integer isDiscount;

    /**
     * 折扣价格
     */
    @TableField("discount_price")
    private BigDecimal discountPrice;

    /**
     * 是否完结 1是 0否
     */
    @TableField("is_finish")
    private Integer isFinish;

    /**
     * 学习人数
     */
    @TableField("learn_persons")
    private Integer learnPersons;

    /**
     * 审核状态 0未审核 1审核通过 2审核不通过
     */
    @TableField("audit_status")
    private Integer auditStatus;

    /**
     * 审核备注
     */
    @TableField("audit_notice")
    private String auditNotice;

    /**
     * 是否上架 1是 0否
     */
    @TableField("is_shelves")
    private Integer isShelves;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 创建人
     */
    @TableField("created_by")
    private Integer createdBy;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private Integer updatedBy;


}
