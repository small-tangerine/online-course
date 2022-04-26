package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 分类
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class CategoryVo  implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Integer id;

    /**
     * 父级ID
     */
    private Integer parentId;
    private String  parentTitle;
    private Integer parentDisplayOrder;
    /**
     * 分类名称
     */
    private String title;

    /**
     * 排序
     */
    private Integer displayOrder;

    /**
     * 创建时间
     */

    private LocalDateTime createdAt;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 更新人
     */
    private Integer updatedBy;


    private List<CategoryVo> children;
}
