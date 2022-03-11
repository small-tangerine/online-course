package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.Teachers;

import java.util.Collection;
import java.util.Map;

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

    Map<Integer, Teachers> findMapByTeacherIds(Collection<Integer> teacherIds);
}
