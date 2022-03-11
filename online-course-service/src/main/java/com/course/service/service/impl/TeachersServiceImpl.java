package com.course.service.service.impl;

import com.course.api.entity.Teachers;
import com.course.service.mapper.TeachersMapper;
import com.course.service.service.TeachersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class TeachersServiceImpl extends ServiceImpl<TeachersMapper, Teachers> implements TeachersService {

    @Override
    public Teachers getByUserId(Integer userId) {
        return lambdaQuery().eq(Teachers::getUserId, userId)
                .last("limit 1")
                .one();
    }

    @Override
    public Map<Integer, Teachers> findMapByTeacherIds(Collection<Integer> teacherIds) {
        if (CollectionUtils.isEmpty(teacherIds)) {
            return Collections.emptyMap();
        }
        return lambdaQuery().in(Teachers::getId, teacherIds)
                .list().stream().collect(Collectors.toMap(Teachers::getId, Function.identity()));
    }
}
