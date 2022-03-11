package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.UserCourse;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 用户课程关联 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserCourseService extends IService<UserCourse> {

    Map<Integer, UserCourse> findMapByCourseIds(Collection<Integer> courseIds, Integer userId);
}
