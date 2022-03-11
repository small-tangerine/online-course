package com.course.service.service.impl;

import com.course.api.entity.User;
import com.course.commons.model.Paging;
import com.course.service.mapper.UserMapper;
import com.course.service.service.UserService;
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
 * 用户 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username)
                .or().eq(User::getMobile, username)
                .eq(User::getEmail, username)
                .last("limit 1")
                .one();
    }

    @Override
    public User findAdminByUsername(String username) {
        return baseMapper.findAdminByUsername(username);
    }

    @Override
    public void findByMap(Paging<User> paging, Map<String, Object> map) {
        baseMapper.finByMap(paging, map);
    }

    @Override
    public Map<Integer, User> findMapByUserIds(Collection<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        return lambdaQuery().in(User::getId, userIds)
                .select(User::getId, User::getUsername, User::getNickname, User::getMobile, User::getEmail)
                .list().stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
