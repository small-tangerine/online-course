package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class CartsVo  {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 课程ID
     */
    private Integer courseId;

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

    /**
     * 封面图片
     */
    private String img;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
