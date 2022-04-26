package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.UserCourse;
import com.course.commons.model.Paging;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 用户课程关联 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface UserCourseService extends IService<UserCourse> {

    Map<Integer, UserCourse> findMapByCourseIds(Collection<Integer> courseIds, Integer userId);

    void listPagingByMap(Paging<UserCourse> paging, Map<String, Object> paramsMap);

    UserCourse getByUserIdAndCourseId(Integer userId, Integer courseId);
}
