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
public class CourseVo implements Serializable {

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
     * 简介
     */
    private String introduction;

    /**
     * 背景图
     */
    private String bgImg;

    /**
     * 封面图
     */
    private String banner;

    /**
     * 课程须知
     */
    private String tip;
    private List<String> tips;

    /**
     * 能学到什么
     */
    private String learnWhat;
    private List<String> learn;

    /**
     * 课程类型 1免费 2付费
     */
    private Integer type;

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
     * 是否完结 1是 0否
     */
    private Integer isFinish;

    /**
     * 学习人数
     */
    private Integer learnPersons;

    /**
     * 审核状态 0未审核 1审核通过 2审核不通过
     */
    private Integer auditStatus;

    /**
     * 审核备注
     */
    private String auditNotice;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    private Teachers teacher;

    private List<CourseVideoListVo> videos;

    private Long length;

    private Integer isBuy;
}
