package com.course.component.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Category;
import com.course.api.entity.CourseCategory;
import com.course.service.service.CategoryService;
import com.course.service.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * 分类
 *
 * @since 2022-03-09
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class CategoryComponent {
    private final CategoryService categoryService;
    private final CourseCategoryService courseCategoryService;

    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Collection<Integer> ids) {
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.in(Category::getParentId, ids)
                .or().in(Category::getId, ids);
        categoryService.remove(query);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(Category updateCategory, boolean isUpdate) {
        categoryService.updateById(updateCategory);
        if (!isUpdate) {
            LambdaUpdateWrapper<CourseCategory> update = Wrappers.lambdaUpdate();
            update.eq(CourseCategory::getCategoryId, updateCategory.getId())
                    .set(CourseCategory::getTitle, updateCategory.getTitle());
            courseCategoryService.update(update);
        }
    }
}
