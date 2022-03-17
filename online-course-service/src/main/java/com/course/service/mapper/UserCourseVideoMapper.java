package com.course.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.api.entity.UserCourseVideo;
import com.course.commons.model.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 用户课程视频关联 Mapper 接口
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserCourseVideoMapper extends BaseMapper<UserCourseVideo> {
    int increaseByColumn(@Param("column") String column, @Param("ids") Collection<Integer> ids, @Param("incr") int incr);

    Paging<UserCourseVideo> listPagingByMap(@Param("paging") Paging<UserCourseVideo> paging, @Param("paramsMap") Map<String, Object> paramsMap);
}
