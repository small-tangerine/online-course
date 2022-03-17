package com.course.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_video")
public class CourseVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程ID
     */
    @TableField("course_id")
    private Integer courseId;

    /**
     * 视频名称
     */
    @TableField("title")
    private String title;

    /**
     * 文件路径
     */
    @TableField("file_url")
    private String fileUrl;

    /**
     * 视频大小
     */
    @TableField("file_size")
    private Integer fileSize;

    /**
     * 封面图片
     */
    @TableField("thumb_url")
    private String thumbUrl;

    /**
     * 播放地址
     */
    @TableField("url")
    private String url;

    /**
     * 视频长度
     */
    @TableField("video_length")
    private Long videoLength;

    /**
     * 排序
     */
    @TableField("display_order")
    private Integer displayOrder;

    /**
     * 审核状态 0未审核 1审核通过 2审核不通过
     */
    @TableField("audit_status")
    private Integer auditStatus;

    /**
     * 审核备注
     */
    @TableField("audit_notice")
    private String auditNotice;

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

    /**
     * 更新人
     */
    @TableField("updated_by")
    private Integer updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;


}
