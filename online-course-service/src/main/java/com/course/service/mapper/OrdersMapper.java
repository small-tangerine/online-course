package com.course.service.mapper;

import com.course.api.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> listByTeacherId(@Param("teacherId") Integer id);
}
