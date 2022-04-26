package com.course.service.service.impl;

import com.course.api.entity.CourseVideo;
import com.course.commons.enums.StatusEnum;
import com.course.commons.model.Paging;
import com.course.service.mapper.CourseVideoMapper;
import com.course.service.service.CourseVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频关联 服务实现类
 * </p>
 *
 * @since 2022-03-02
 */
@Service
public class CourseVideoServiceImpl extends ServiceImpl<CourseVideoMapper, CourseVideo> implements CourseVideoService {

    @Override
    public List<CourseVideo> listByCourseId(Integer courseId) {
        return lambdaQuery().eq(CourseVideo::getCourseId,courseId)
                .eq(CourseVideo::getAuditStatus, StatusEnum.SUCCESS.getStatus())
                .list();
    }

    @Override
    public void pagingByParamsMap(Paging<CourseVideo> paging, Map<String, Object> paramsMap) {
        baseMapper.pagingByParamsMap(paging,paramsMap);
    }
}
