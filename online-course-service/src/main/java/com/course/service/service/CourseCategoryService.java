package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.CourseCategory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程分类关联 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    Map<Integer, List<CourseCategory>> findMapByCourseIds(Collection<Integer> courseIds);
}
