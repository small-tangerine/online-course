package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.dto.CourseDto;
import com.course.api.entity.Course;
import com.course.commons.model.Paging;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface CourseService extends IService<Course> {

    Map<Integer,Course> findMapByIds(Collection<Integer> courseIds);

    void listByParamsMap(Paging<Course> paging, Map<String, Object> paramsMap);

    int increaseByColumn(String column, Collection<Integer> courseIds, int incr);

   void listByUserCourse(Paging<Course>paging,Integer type, Integer userId);

    List<CourseDto> countCourse(Map<String, Object> paramsMap);

    void pagingByMap(Paging<Course> paging, Map<String, Object> paramsMap);

    List<Course> listByParamsMap(Map<String, Object> paramMap);

    Course getByAlias(String alias);
}
