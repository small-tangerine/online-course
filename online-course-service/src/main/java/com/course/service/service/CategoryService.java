package com.course.service.service;

import com.course.api.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface CategoryService extends IService<Category> {

    int findNextDisplayOrder(Integer parentId);

    List<Integer> getParentById(Collection<Integer> categoryIds);
}
