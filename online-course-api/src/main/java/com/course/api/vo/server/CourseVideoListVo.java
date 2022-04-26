package com.course.api.vo.server;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程视频关联
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class CourseVideoListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 视频名称
     */
    private String title;

    /**
     * 排序
     */
    private Integer displayOrder;

    private Long videoLength;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    private String persons;
    private String personsTitle;
    private Integer isFinish;
    private Long current;
}
