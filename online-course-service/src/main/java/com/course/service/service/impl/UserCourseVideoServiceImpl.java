package com.course.service.service.impl;

import com.course.api.dto.CountDto;
import com.course.api.entity.UserCourseVideo;
import com.course.commons.model.Paging;
import com.course.service.mapper.UserCourseVideoMapper;
import com.course.service.service.UserCourseVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户课程视频关联 服务实现类
 * </p>
 *
 * @since 2022-03-02
 */
@Service
public class UserCourseVideoServiceImpl extends ServiceImpl<UserCourseVideoMapper, UserCourseVideo> implements UserCourseVideoService {

    @Override
    public Map<Integer, List<UserCourseVideo>> findMapByUserIdsAndCourseId(Collection<Integer> userIds, Integer courseId) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        return lambdaQuery().eq(UserCourseVideo::getCourseId, courseId)
                .in(UserCourseVideo::getUserId, userIds)
                .list().stream().collect(Collectors.groupingBy(UserCourseVideo::getUserId));
    }

    @Override
    public Map<Integer, List<UserCourseVideo>> findMapByCourseIdAndVideoIds(Integer id, Collection<Integer> videoIds) {
        if (CollectionUtils.isEmpty(videoIds)) {
            return Collections.emptyMap();
        }
        return lambdaQuery().eq(UserCourseVideo::getCourseId, id)
                .in(UserCourseVideo::getVideoId, videoIds)
                .list().stream().collect(Collectors.groupingBy(UserCourseVideo::getVideoId));
    }

    @Override
    public UserCourseVideo getByUserIdAndVideoId(Integer userId, Integer id) {
        return lambdaQuery().eq(UserCourseVideo::getUserId, userId)
                .eq(UserCourseVideo::getVideoId, id)
                .last("limit 1")
                .one();
    }

    @Override
    public void increaseByColumn(String cumulativeDuration, List<Integer> singletonList, int i) {
        baseMapper.increaseByColumn(cumulativeDuration, singletonList, i);
    }

    @Override
    public Map<Integer, List<UserCourseVideo>> findMapByUserIdAndCourseIds(Integer userId, Set<Integer> courseId) {
        if (CollectionUtils.isEmpty(courseId)) {
            return Collections.emptyMap();
        }
        return lambdaQuery().eq(UserCourseVideo::getUserId, userId)
                .in(UserCourseVideo::getCourseId, courseId)
                .list().stream().collect(Collectors.groupingBy(UserCourseVideo::getCourseId));
    }

    @Override
    public void listPagingByMap(Paging<UserCourseVideo> paging, Map<String, Object> paramsMap) {
        baseMapper.listPagingByMap(paging, paramsMap);
    }

    @Override
    public Map<Integer, Integer> countLearn(Set<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        List<CountDto> countDtoList = baseMapper.countLearn(userIds);
        return countDtoList.stream().collect(Collectors.toMap(CountDto::getId, CountDto::getCount));
    }
}
