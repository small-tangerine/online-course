package com.course.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.api.dto.CountDto;
import com.course.api.entity.UserCourseVideo;
import com.course.commons.model.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户课程视频关联 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface UserCourseVideoMapper extends BaseMapper<UserCourseVideo> {
    int increaseByColumn(@Param("column") String column, @Param("ids") Collection<Integer> ids, @Param("incr") int incr);

    Paging<UserCourseVideo> listPagingByMap(@Param("paging") Paging<UserCourseVideo> paging, @Param("paramsMap") Map<String, Object> paramsMap);

    List<CountDto> countLearn(@Param("list") Set<Integer> userIds);
}
