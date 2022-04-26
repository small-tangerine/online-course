package com.course.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.api.dto.CourseDto;
import com.course.api.entity.Course;
import com.course.commons.enums.StatusEnum;
import com.course.commons.model.Paging;
import com.course.service.mapper.CourseMapper;
import com.course.service.service.CourseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @since 2022-03-02
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public Map<Integer, Course> findMapByIds(Collection<Integer> courseIds) {
        if (CollectionUtils.isEmpty(courseIds)) {
            return Collections.emptyMap();
        }
        return lambdaQuery().in(Course::getId, courseIds)
                .eq(Course::getAuditStatus, StatusEnum.SUCCESS.getStatus())
                .list().stream().collect(Collectors.toMap(Course::getId, Function.identity()));
    }

    @Override
    public void listByParamsMap(Paging<Course> paging, Map<String, Object> paramsMap) {
        baseMapper.listByParamsMap(paging, paramsMap);
    }

    @Override
    public int increaseByColumn(String column, Collection<Integer> courseIds, int incr) {
        return baseMapper.increaseByColumn(column, courseIds, incr);
    }

    @Override
    public void listByUserCourse(Paging<Course> paging, Integer type, Integer userId) {
        baseMapper.listByUserCourse(paging, type, userId);
    }

    @Override
    public List<CourseDto> countCourse(Map<String, Object> paramsMap) {
        return baseMapper.countCourse(paramsMap);
    }

    @Override
    public void pagingByMap(Paging<Course> paging, Map<String, Object> paramsMap) {
        baseMapper.pagingByMap(paging,paramsMap);
    }

    @Override
    public List<Course> listByParamsMap(Map<String, Object> paramMap) {
        return baseMapper.listParamsMap(paramMap);
    }

    @Override
    public Course getByAlias(String alias) {
        return lambdaQuery().eq(Course::getAlias,alias)
                .last("limit 1")
                .one();
    }
}
