package com.course.api.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单vo
 *
 * @author panguangming
 * @since 2022-03-08
 */
@Data
@Accessors(chain = true)
public class MenuVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer parentId;
    private Integer displayOrder;
    private String label;
    private String path;
    private List<MenuVo> children;
}
