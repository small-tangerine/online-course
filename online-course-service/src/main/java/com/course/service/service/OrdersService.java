package com.course.service.service;

import com.course.api.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @since 2022-03-02
 */
public interface OrdersService extends IService<Orders> {

    Orders getOrderByCode(String code);

    List<Orders> listByTeacherId(Integer id);
}
