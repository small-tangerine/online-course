package com.course.service.service;

import com.course.api.entity.HomeRecommend;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页推荐 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface HomeRecommendService extends IService<HomeRecommend> {

    HomeRecommend getByCourseId(Integer id);

    HomeRecommend getByCourseIdAndType(Integer id, Integer recommend);
}
