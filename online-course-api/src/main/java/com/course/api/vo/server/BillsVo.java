package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 账单
 * </p>
 *
 * @author panguangming
 * @since 2022-03-04
 */
@Data
@Accessors(chain = true)
public class BillsVo{

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 课程名称
     */
    private String title;

    /**
     * 消费金额
     */
    private BigDecimal cost;

    /**
     * 支付方式 0余额支付 1支付宝 2微信
     */
    private Integer payType;
    private String payTypeTitle;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
