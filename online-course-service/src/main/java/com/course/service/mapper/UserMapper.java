package com.course.service.mapper;

import com.course.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface UserMapper extends BaseMapper<User> {

    User findAdminByUsername(@Param("username") String username);
}
