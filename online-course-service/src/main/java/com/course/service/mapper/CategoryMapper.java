package com.course.service.mapper;

import com.course.api.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 分类 Mapper 接口
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface CategoryMapper extends BaseMapper<Category> {

    Integer findMaxDisplayOrder(@Param("parentId") Integer parentId);
}
