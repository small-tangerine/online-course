package com.course.service.service.impl;

import com.course.api.entity.Orders;
import com.course.service.mapper.OrdersMapper;
import com.course.service.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public Orders getOrderByCode(String code) {
        return lambdaQuery().eq(Orders::getCode, code)
                .last("limit 1")
                .one();
    }

    @Override
    public List<Orders> listByTeacherId(Integer id) {
        return baseMapper.listByTeacherId(id);
    }
}
