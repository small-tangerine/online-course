package com.course.service.service.impl;

import com.course.api.entity.Teachers;
import com.course.service.mapper.TeachersMapper;
import com.course.service.service.TeachersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
        return lambdaQuery().eq(Teachers::getUserId,userId)
                .last("limit 1")
                .one();
    }
}
