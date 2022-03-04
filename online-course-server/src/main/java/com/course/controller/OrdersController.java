package com.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Orders;
import com.course.api.entity.OrdersDetail;
import com.course.api.vo.server.OrdersDetailVo;
import com.course.api.vo.server.OrdersVo;
import com.course.commons.enums.PayStatusEnum;
import com.course.commons.enums.PayTypeEnum;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.config.security.SecurityUtils;
import com.course.service.service.OrdersDetailService;
import com.course.service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author panguangming
 * @since 2022-03-04
 */
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrdersController {
    private final OrdersDetailService ordersDetailService;
    private final OrdersService ordersService;
    private final MapperFacade mapperFacade;

    /**
     * 订单列表
     *
     * @param page   页码
     * @param size   页大小
     * @param status 状态{@link PayStatusEnum}
     * @return response
     */
    @GetMapping("/list")
    public Response orderList(Integer page, Integer size, Integer status) {
        Integer userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<Orders> query = Wrappers.lambdaQuery();
        query.eq(Orders::getUserId, userId)
                .eq(Objects.nonNull(status), Orders::getPayStatus, status)
                .orderByAsc(Orders::getPayStatus)
                .orderByDesc(Orders::getId);
        Paging<Orders> paging = new Paging<>(page, size);
        ordersService.page(paging, query);
        Set<Integer> orderIds = paging.getItems().stream().map(Orders::getId).collect(Collectors.toSet());
        Map<Integer, List<OrdersDetail>> ordersMap = ordersDetailService.mapListByOrderIds(orderIds);
        paging.convert(item -> {
            OrdersVo map = mapperFacade.map(item, OrdersVo.class);
            map.setPayStatusTitle(PayStatusEnum.getDescFromStatus(item.getPayStatus()))
                    .setPayTypeTitle(PayTypeEnum.getDescFromType(item.getPayType()));
            List<OrdersDetail> ordersDetails = ordersMap.get(item.getId());
            wrapDetail(map,ordersDetails);
            return map;
        });
        return Response.ok(paging);
    }

    /**
     * 删除订单
     *
     * @param order 订单实体
     * @return response
     */
    @PostMapping("/delete")
    public Response orderDelete(@RequestBody Orders order) {
        Assert.notNull(order.getId(), "无效的订单ID");
        Integer userId = SecurityUtils.getUserId();
        Orders ordersServiceById = ordersService.getById(order.getId());
        Assert.isTrue(Objects.equals(userId, ordersServiceById.getUserId()), "非法操作");
        LambdaQueryWrapper<Orders> query = Wrappers.lambdaQuery();
        query.eq(Orders::getUserId, userId)
                .eq(Orders::getId, order.getId());
        ordersService.remove(query);
        return ResponseHelper.deleteSuccess();
    }

    /**
     * 取消订单
     *
     * @param order 订单实体
     * @return response
     */
    @PostMapping("/cancel")
    public Response orderCancel(@RequestBody Orders order) {
        Assert.notNull(order.getId(), "无效的订单ID");
        Integer userId = SecurityUtils.getUserId();
        Orders ordersServiceById = ordersService.getById(order.getId());
        Assert.notNull(ordersServiceById, "该订单不存在");
        Assert.isFalse(PayStatusEnum.PAY.equalsStatus(ordersServiceById.getPayStatus()), "该订单已完成,无法取消");
        Assert.isTrue(Objects.equals(userId, ordersServiceById.getUserId()), "非法操作");
        Assert.isFalse(PayStatusEnum.CANCEL.equalsStatus(ordersServiceById.getPayStatus()), "该订单已取消");
        ordersService.updateById(new Orders().setId(ordersServiceById.getId()).setPayStatus(PayStatusEnum.CANCEL.getStatus()));
        return Response.ok("取消成功");
    }

    @GetMapping("/detail")
    public Response orderDetail(@NotBlank(message = "订单编号不能为空") String code) {
        Integer userId = SecurityUtils.getUserId();
        Orders ordersServiceByCode = ordersService.getOrderByCode(code);
        Assert.notNull(ordersServiceByCode, "该订单不存在");
        Assert.isTrue(Objects.equals(userId, ordersServiceByCode.getUserId()), "非法操作");
        List<OrdersDetail> detailList = ordersDetailService.listByOrderId(ordersServiceByCode.getId());
        OrdersVo map = mapperFacade.map(ordersServiceByCode, OrdersVo.class);
        map.setPayStatusTitle(PayStatusEnum.getDescFromStatus(ordersServiceByCode.getPayStatus()));
        wrapDetail(map,detailList);
        return Response.ok(map);
    }

    private void wrapDetail(OrdersVo map, List<OrdersDetail> detailList) {
        map.setCost(BigDecimal.ZERO);
        List<OrdersDetailVo> ordersDetailVoList = detailList.stream()
                .map(details -> {
                    OrdersDetailVo detailVo = mapperFacade.map(details, OrdersDetailVo.class);
                    if (YesOrNoEnum.YES.equalsStatus(details.getIsDiscount())) {
                        BigDecimal discountPrice = details.getDiscountPrice();
                        map.setCost(map.getCost().add(discountPrice));
                    } else {
                        BigDecimal price = details.getPrice();
                        map.setCost(map.getCost().add(price));
                    }
                    return detailVo;
                })
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(detailList)) {
            map.setList(ordersDetailVoList);
        }
    }
}