package com.course.service.mapper;

import com.course.api.entity.OrdersDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单详情 Mapper 接口
 * </p>
 *
 * @since 2022-03-02
 */
public interface OrdersDetailMapper extends BaseMapper<OrdersDetail> {

    List<OrdersDetail> listByTeacherId(@Param("teacherId") Integer id);

    List<OrdersDetail> listByParamsMap(@Param("paramsMap") Map<String, Object> paramsMap);
}
