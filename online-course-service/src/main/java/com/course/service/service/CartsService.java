package com.course.service.service;

import com.course.api.entity.Carts;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface CartsService extends IService<Carts> {

    Carts getByUserIdAndCourseId(Integer userId, Integer courseId);
}
