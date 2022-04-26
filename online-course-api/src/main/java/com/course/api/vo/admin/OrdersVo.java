package com.course.api.vo.admin;

import lombok.Data;
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
@Accessors(chain = true)
public class OrdersVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 用户ID
     */
    private Integer userId;
    private String userTitle;
    /**
     * 用户ID
     */
    private BigDecimal cost;
    /**
     * 支付状态 0未支付 1已完成 2已过期
     */
    private Integer payStatus;
    private String payStatusTitle;

    /**
     * 支付方式 1余额支付 2支付宝 3微信
     */
    private Integer payType;
    private String payTypeTitle;

    /**
     * 失效时间
     */
    private LocalDateTime expiredAt;

    /**
     * 失效时间
     */
    private LocalDateTime cancelAt;
    /**
     * 支付时间
     */
    private LocalDateTime payAt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
