package com.course.service.service.impl;

import com.course.api.entity.HomeRecommend;
import com.course.service.mapper.HomeRecommendMapper;
import com.course.service.service.HomeRecommendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页推荐 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class HomeRecommendServiceImpl extends ServiceImpl<HomeRecommendMapper, HomeRecommend> implements HomeRecommendService {

    @Override
    public HomeRecommend getByCourseId(Integer id) {
        return lambdaQuery().eq(HomeRecommend::getCourseId,id)
                .last("limit 1")
                .one();
    }

    @Override
    public HomeRecommend getByCourseIdAndType(Integer id, Integer recommend) {
        return lambdaQuery().eq(HomeRecommend::getCourseId,id)
                .eq(HomeRecommend::getType,recommend)
                .last("limit 1")
                .one();
    }
}
