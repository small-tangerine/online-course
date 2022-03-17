package com.course.api.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 1,max = 64,message = "标题长度不能超过64个字")
    private String title;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 视频大小
     */
    private Long fileSize;
    private String fileSizeTitle;

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
     * 排序
     */
    private Integer displayOrder;

    /**
     * 审核状态 0未审核 1审核通过 2审核不通过
     */
    private Integer auditStatus;
    private String auditStatusTitle;

    /**
     * 审核备注
     */
    private String auditNotice;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    private Integer learnPersons;
}
