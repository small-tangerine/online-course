package com.course.component.component;

import com.course.api.entity.Orders;
import com.course.api.entity.OrdersDetail;
import com.course.service.service.CartsService;
import com.course.service.service.OrdersDetailService;
import com.course.service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 订单
 *
 * @author panguangming
 * @since 2022-03-04
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class OrdersComponent {
    private final OrdersService ordersService;
    private final OrdersDetailService ordersDetailService;
    private final CartsService cartsService;

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Orders orders, List<OrdersDetail> details, Set<Integer> cartsIds) {
        ordersService.save(orders);
        if (CollectionUtils.isNotEmpty(details)) {
            details.forEach(item -> item.setOrderId(orders.getId()));
            ordersDetailService.saveBatch(details);
        }
        if (CollectionUtils.isNotEmpty(cartsIds)) {
            cartsService.removeByIds(cartsIds);
        }
    }
}
