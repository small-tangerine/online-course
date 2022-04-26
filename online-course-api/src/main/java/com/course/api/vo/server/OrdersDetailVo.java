package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 订单详情
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class OrdersDetailVo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 封面
     */
    private String img;

    /**
     * 标题
     */
    private String title;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否折扣 1是 0否
     */
    private Integer isDiscount;

    /**
     * 折扣价格
     */
    private BigDecimal discountPrice;
}
