package com.course.service.service.impl;

import com.course.api.entity.OrdersDetail;
import com.course.service.mapper.OrdersDetailMapper;
import com.course.service.service.OrdersDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单详情 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class OrdersDetailServiceImpl extends ServiceImpl<OrdersDetailMapper, OrdersDetail> implements OrdersDetailService {

    @Override
    public Map<Integer, List<OrdersDetail>> mapListByOrderIds(Collection<Integer> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return Collections.emptyMap();
        }
        List<OrdersDetail> list = lambdaQuery().in(OrdersDetail::getOrderId, orderIds)
                .list();
        return list.stream().collect(Collectors.groupingBy(OrdersDetail::getOrderId));
    }

    @Override
    public List<OrdersDetail> listByOrderId(Integer id) {
        return lambdaQuery().eq(OrdersDetail::getOrderId, id)
                .list();
    }
}
