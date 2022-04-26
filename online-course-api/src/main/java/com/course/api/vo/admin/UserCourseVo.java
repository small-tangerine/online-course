package com.course.api.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户课程关联
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class UserCourseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;
    private String userTitle;

    /**
     * 课程ID
     */
    private Integer courseId;
    private String courseTitle;


    private Long learnLength;

    private BigDecimal cost;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    private LocalDateTime recentAt;

}
