package com.course.component.component;

import com.course.api.entity.*;
import com.course.commons.utils.Assert;
import com.course.service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    private final BillsService billsService;
    private final RechargesService rechargesService;
    private final UserCourseService userCourseService;
    private final CourseService courseService;

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

    @Transactional(rollbackFor = Exception.class)
    public void pay(Orders updateOrders, Recharges recharges, List<Bills> billsList,
                    List<UserCourse> userCourseList, Set<Integer> courseIds) {
        ordersService.updateById(updateOrders);

        if (Objects.nonNull(recharges)) {
            rechargesService.save(recharges);
        }

        billsService.saveBatch(billsList);

        userCourseService.saveBatch(userCourseList);

        int learn_persons = courseService.increaseByColumn("learn_persons", courseIds, 1);
        Assert.equals(learn_persons, courseIds.size(), "订单支付失败");
    }
}
