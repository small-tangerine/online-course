package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单详情vo
 *
 * @author panguangming
 * @since 2022-03-04
 */
@Data
@Accessors(chain = true)
public class OrdersVo {

    private Integer id;
    private String code;
    private LocalDateTime expiredAt;
    private Integer payStatus;
    private String payStatusTitle;
    private Integer payType;
    private String payTypeTitle;
    private LocalDateTime createdAt;
    private Integer userId;
    private BigDecimal cost;
    private List<OrdersDetailVo> list;
}
