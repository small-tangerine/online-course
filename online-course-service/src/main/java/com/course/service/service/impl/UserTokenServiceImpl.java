package com.course.service.service.impl;

import com.course.api.entity.UserToken;
import com.course.service.mapper.UserTokenMapper;
import com.course.service.service.UserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * token记录 服务实现类
 * </p>
 *
 * @since 2022-03-02
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

    @Override
    public UserToken findByUserIdAndType(Integer id, Integer typeId) {
        return lambdaQuery().eq(UserToken::getUserId, id)
                .eq(UserToken::getType, typeId)
                .last("limit 1")
                .one();
    }
}
