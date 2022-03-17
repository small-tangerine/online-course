package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Orders;
import com.course.api.entity.OrdersDetail;
import com.course.api.entity.User;
import com.course.api.vo.admin.OrdersVo;
import com.course.commons.enums.PayStatusEnum;
import com.course.commons.enums.PayTypeEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.service.service.OrdersDetailService;
import com.course.service.service.OrdersService;
import com.course.service.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author panguangming
 * @since 2022-03-11
 */
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrdersController {
    private final OrdersService ordersService;
    private final UserService userService;
    private final MapperFacade mapperFacade;
    private final OrdersDetailService ordersDetailService;

    @GetMapping("/list")
    public Response orderList(Integer page, Integer pageSize, String code, Integer payType, Integer payStatus) {
        Paging<Orders> paging = new Paging<>(page, pageSize);
        LambdaQueryWrapper<Orders> query = Wrappers.lambdaQuery();
        query.eq(Objects.nonNull(payStatus), Orders::getPayStatus, payStatus)
                .eq(Objects.nonNull(payType), Orders::getPayType, payType)
                .like(StringUtils.isNotBlank(code), Orders::getCode, StringUtils.trim(code));
        ordersService.page(paging, query);
        Set<Integer> userIds = paging.getItems().stream().map(Orders::getUserId).collect(Collectors.toSet());
        Map<Integer, User> userMap = userService.findMapByUserIds(userIds);
        BigDecimal payCost = paging.getItems().stream().filter(item -> PayStatusEnum.PAY.equalsStatus(item.getPayStatus()))
                .map(Orders::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = paging.getItems().stream().map(Orders::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        paging.setExtra(ImmutableMap.of("payCost", payCost, "totalCost", totalCost));
        paging.convert(item -> {
            OrdersVo map = mapperFacade.map(item, OrdersVo.class);
            User user = userMap.get(item.getUserId());
            map.setPayStatusTitle(PayStatusEnum.getDescFromStatus(item.getPayStatus()))
                    .setPayTypeTitle(PayTypeEnum.getDescFromType(item.getPayType()));
            if (Objects.nonNull(user)) {
                return map.setUserTitle(user.getNickname());
            }
            return map.setUserTitle("用户已删除");
        });
        return Response.ok(paging);
    }

    @GetMapping("/detail-list")
    public Response orderDetailList(@NotNull(message = "订单编号不能为空") Integer id) {
        Orders byId = ordersService.getById(id);
        Assert.notNull(byId, "订单不存在");
        List<OrdersDetail> details = ordersDetailService.listByOrderId(byId.getId());
        return Response.ok(details);
    }
}
