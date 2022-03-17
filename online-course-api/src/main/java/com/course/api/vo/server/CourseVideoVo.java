package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程视频关联
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class CourseVideoVo implements Serializable {

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
     * 封面图片
     */
    private String thumbUrl;

    /**
     * 播放地址
     */
    private String url;

    /**
     * 视频长度
     */
    private Long videoLength;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
