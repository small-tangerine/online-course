package com.course.service.service.impl;

import com.course.api.entity.UserRole;
import com.course.service.mapper.UserRoleMapper;
import com.course.service.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Integer getIdByUserId(Integer id) {
        UserRole one = lambdaQuery().eq(UserRole::getUserId, id)
                .last("limit 1")
                .one();
        return Objects.isNull(one) ? 0 : one.getRoleId();
    }

    @Override
    public Map<Integer, Integer> findMapByUserIds(Collection<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)){
            return Collections.emptyMap();
        }
        return lambdaQuery().in(UserRole::getUserId,userIds)
                .list().stream().collect(Collectors.toMap(UserRole::getUserId, UserRole::getRoleId));
    }
}
