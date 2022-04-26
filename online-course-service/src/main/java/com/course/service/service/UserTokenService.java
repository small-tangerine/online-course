package com.course.service.service;

import com.course.api.entity.UserToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * token记录 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface UserTokenService extends IService<UserToken> {

    UserToken findByUserIdAndType(Integer id, Integer typeId);
}
