package com.course.api.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户课程视频关联
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class UserCourseVideoVo implements Serializable {

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

    /**
     * 视频ID
     */
    private Integer videoId;
    private String videoTitle;

    /**
     * 当前学习时长
     */
    private Long learnLength;

    /**
     * 累计学习时长
     */
    private Long cumulativeDuration;

    /**
     * 学习状态 0未学习 1已完成 2学习中
     */
    private Integer learnStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
