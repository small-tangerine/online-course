package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.UserRole;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserRoleService extends IService<UserRole> {

    Integer getIdByUserId(Integer id);

    Map<Integer, Integer> findMapByUserIds(Collection<Integer> userIds);
}
