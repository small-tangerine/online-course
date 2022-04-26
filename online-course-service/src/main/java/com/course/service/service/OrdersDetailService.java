package com.course.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.course.api.entity.OrdersDetail;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单详情 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface OrdersDetailService extends IService<OrdersDetail> {

    Map<Integer, List<OrdersDetail>> mapListByOrderIds(Collection<Integer> orderIds);

    List<OrdersDetail> listByOrderId(Integer id);

    List<OrdersDetail> listByTeacherId(Integer id);

    List<OrdersDetail> listByParamsMap(Map<String, Object> paramsMap);
}
