package com.course.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户课程视频关联
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_course_video")
public class UserCourseVideo implements Serializable {

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
     * 课程ID
     */
    @TableField("course_id")
    private Integer courseId;

    /**
     * 视频ID
     */
    @TableField("video_id")
    private Integer videoId;

    /**
     * 当前学习时长
     */
    @TableField("learn_length")
    private Integer learnLength;

    /**
     * 累计学习时长
     */
    @TableField("cumulative_duration")
    private Integer cumulativeDuration;

    /**
     * 学习状态 0未学习 1已完成 2学习中
     */
    @TableField("learn_status")
    private Integer learnStatus;

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
