package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.Course;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface CourseService extends IService<Course> {

    Map<Integer,Course> findMapByIds(Collection<Integer> courseIds);
}
