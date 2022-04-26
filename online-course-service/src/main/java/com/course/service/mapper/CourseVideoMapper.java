package com.course.service.mapper;

import com.course.api.entity.CourseVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.commons.model.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 课程视频关联 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface CourseVideoMapper extends BaseMapper<CourseVideo> {

    Paging<CourseVideo> pagingByParamsMap(@Param("paging") Paging<CourseVideo> paging,@Param("paramsMap") Map<String, Object> paramsMap);
}
