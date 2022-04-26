package com.course.service.mapper;

import com.course.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.commons.model.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface UserMapper extends BaseMapper<User> {

    User findAdminByUsername(@Param("username") String username);

    Paging<User> finByMap(@Param("paging") Paging<User> paging, @Param("map") Map<String, Object> map);
}
