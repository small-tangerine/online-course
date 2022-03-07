package com.course.service.service;

import com.course.api.entity.Teachers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface TeachersService extends IService<Teachers> {

    Teachers getByUserId(Integer userId);
}
