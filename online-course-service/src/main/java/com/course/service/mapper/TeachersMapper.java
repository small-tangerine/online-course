package com.course.service.mapper;

import com.course.api.entity.Teachers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface TeachersMapper extends BaseMapper<Teachers> {

    Teachers getByCourseId(@Param("courseId") Integer courseId);
}
