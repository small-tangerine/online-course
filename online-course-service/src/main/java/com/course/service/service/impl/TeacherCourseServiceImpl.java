package com.course.service.service.impl;

import com.course.api.entity.TeacherCourse;
import com.course.service.mapper.TeacherCourseMapper;
import com.course.service.service.TeacherCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 讲师课程关联 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class TeacherCourseServiceImpl extends ServiceImpl<TeacherCourseMapper, TeacherCourse> implements TeacherCourseService {

    @Override
    public List<TeacherCourse> listByCourseIds(Collection<Integer> courseIds) {
        if (CollectionUtils.isEmpty(courseIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(TeacherCourse::getCourseId, courseIds)
                .list();
    }
}
