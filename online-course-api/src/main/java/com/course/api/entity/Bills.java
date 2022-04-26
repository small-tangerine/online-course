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
 * 账单
 * </p>
 *
 * @since 2022-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bills")
public class Bills implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 订单编号
     */
    @TableField("order_code")
    private String orderCode;

    /**
     * 课程ID
     */
    @TableField("course_id")
    private Integer courseId;

    /**
     * 课程名称
     */
    @TableField("title")
    private String title;

    /**
     * 消费金额
     */
    @TableField("cost")
    private BigDecimal cost;

    /**
     * 支付方式 0余额支付 1支付宝 2微信
     */
    @TableField("pay_type")
    private Integer payType;

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


}
