package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.UserCourseVideo;
import com.course.commons.model.Paging;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户课程视频关联 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserCourseVideoService extends IService<UserCourseVideo> {

    Map<Integer, List<UserCourseVideo>> findMapByUserIdsAndCourseId(Collection<Integer> userIds, Integer courseId);

    Map<Integer, List<UserCourseVideo>> findMapByCourseIdAndVideoIds(Integer id, Collection<Integer> videoIds);

    UserCourseVideo getByUserIdAndVideoId(Integer userId, Integer id);

    void increaseByColumn(String cumulativeDuration, List<Integer> singletonList, int i);

    Map<Integer, List<UserCourseVideo>> findMapByUserIdAndCourseIds(Integer userId, Set<Integer> courseId);

    void listPagingByMap(Paging<UserCourseVideo> paging, Map<String, Object> paramsMap);

    Map<Integer,Integer> countLearn(Set<Integer> userIds);
}
