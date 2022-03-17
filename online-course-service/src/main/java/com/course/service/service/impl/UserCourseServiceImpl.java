package com.course.service.service.impl;

import com.course.api.entity.UserCourse;
import com.course.commons.model.Paging;
import com.course.service.mapper.UserCourseMapper;
import com.course.service.service.UserCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户课程关联 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse> implements UserCourseService {

    @Override
    public Map<Integer, UserCourse> findMapByCourseIds(Collection<Integer> courseIds, Integer userId) {
        if (CollectionUtils.isEmpty(courseIds)||Objects.isNull(userId)){
            return Collections.emptyMap();
        }
        return lambdaQuery().eq(UserCourse::getUserId,userId)
                .in(UserCourse::getCourseId,courseIds)
                .list().stream().collect(Collectors.toMap(UserCourse::getCourseId, Function.identity()));
    }

    @Override
    public void listPagingByMap(Paging<UserCourse> paging, Map<String, Object> paramsMap) {
        baseMapper.listPagingByMap(paging,paramsMap);
    }

    @Override
    public UserCourse getByUserIdAndCourseId(Integer userId, Integer courseId) {
        return lambdaQuery().eq(UserCourse::getUserId,userId)
                .eq(UserCourse::getCourseId,courseId)
                .last("limit 1")
                .one();
    }
}
