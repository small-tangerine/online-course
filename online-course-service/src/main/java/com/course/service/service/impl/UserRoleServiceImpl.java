package com.course.service.service.impl;

import com.course.api.entity.UserRole;
import com.course.service.mapper.UserRoleMapper;
import com.course.service.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}
