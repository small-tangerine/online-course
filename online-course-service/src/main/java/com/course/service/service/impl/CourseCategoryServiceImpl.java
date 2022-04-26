package com.course.service.service.impl;

import com.course.api.entity.CourseCategory;
import com.course.service.mapper.CourseCategoryMapper;
import com.course.service.service.CourseCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类关联 服务实现类
 * </p>
 *
 * @since 2022-03-02
 */
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Override
    public Map<Integer, List<CourseCategory>> findMapByCourseIds(Collection<Integer> courseIds) {
        return lambdaQuery().in(CourseCategory::getCourseId,courseIds)
                .list().stream().collect(Collectors.groupingBy(CourseCategory::getCourseId));
    }
}
