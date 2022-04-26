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
 * 订单
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    @TableField("code")
    private String code;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 用户ID
     */
    @TableField("cost")
    private BigDecimal cost;
    /**
     * 支付状态 0未支付 1已完成 2已过期
     */
    @TableField("pay_status")
    private Integer payStatus;

    /**
     * 支付方式 1余额支付 2支付宝 3微信
     */
    @TableField("pay_type")
    private Integer payType;

    /**
     * 失效时间
     */
    @TableField("expired_at")
    private LocalDateTime expiredAt;

    /**
     * 失效时间
     */
    @TableField("cancel_at")
    private LocalDateTime cancelAt;
    /**
     * 支付时间
     */
    @TableField("pay_at")
    private LocalDateTime payAt;

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
