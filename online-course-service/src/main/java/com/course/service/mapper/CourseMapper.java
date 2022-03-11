package com.course.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.api.entity.Course;
import com.course.commons.model.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface CourseMapper extends BaseMapper<Course> {

    Paging<Course> listByParamsMap(@Param("paging") Paging<Course> paging, @Param("paramsMap") Map<String, Object> paramsMap);

    int increaseByColumn(@Param("column") String column, @Param("ids") Collection<Integer> courseIds, @Param("incr") int incr);

    Paging<Course> listByUserCourse(@Param("paging") Paging<Course> paging, @Param("type") Integer type, @Param("userId") Integer userId);
}
