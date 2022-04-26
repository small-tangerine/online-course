package com.course.service.mapper;

import com.course.api.entity.UserCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.commons.model.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 用户课程关联 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface UserCourseMapper extends BaseMapper<UserCourse> {

    Paging<UserCourse> listPagingByMap(@Param("paging") Paging<UserCourse> paging, @Param("paramsMap") Map<String, Object> paramsMap);
}
