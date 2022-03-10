package com.course.api.vo.server;

import com.course.api.entity.Teachers;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class CourseListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    private String alias;
    /**
     * 标题
     */
    private String title;
    /**
     * 课程类型 1免费 2付费
     */
    private Integer type;
    /**
     * 封面图
     */
    private String banner;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否折扣 1是 -否
     */
    private Integer isDiscount;

    /**
     * 折扣价格
     */
    private BigDecimal discountPrice;

    /**
     * 学习人数
     */
    private Integer learnPersons;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    private List<String> labels;

    private String introduction;

    private String avatar;
    private Teachers teachers;
}
