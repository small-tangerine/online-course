package com.course.api.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * <p>
 * 角色
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class RoleVo implements Serializable {

    /**
     * 角色ID
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String title;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    private Collection<Integer> resourceCollect;
}
