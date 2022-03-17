package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.User;
import com.course.commons.model.Paging;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserService extends IService<User> {

    /**
     * 根据账号查找用户
     * @param username 账号
     * @return user
     */
    User findByUsername(String username);

    User findAdminByUsername(String username);

    void findByMap(Paging<User> paging, Map<String, Object> map);

    Map<Integer, User> findMapByUserIds(Collection<Integer> userIds);

    User findUserNotContain(Integer id, String username);
}
