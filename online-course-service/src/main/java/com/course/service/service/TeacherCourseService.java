package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.TeacherCourse;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 讲师课程关联 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface TeacherCourseService extends IService<TeacherCourse> {

    List<TeacherCourse> listByCourseIds(Collection<Integer> courseIds);
}
