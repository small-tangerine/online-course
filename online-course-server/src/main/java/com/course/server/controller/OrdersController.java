package com.course.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.*;
import com.course.api.vo.server.CartsVo;
import com.course.api.vo.server.OrdersDetailVo;
import com.course.api.vo.server.OrdersVo;
import com.course.commons.enums.PayStatusEnum;
import com.course.commons.enums.PayTypeEnum;
import com.course.commons.enums.RechargeTypeEnum;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.IdUtils;
import com.course.commons.utils.ResponseHelper;
import com.course.component.component.OrdersComponent;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.*;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单
 *
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
    private final OrdersComponent ordersComponent;

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
            wrapDetail(map, ordersDetails);
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
        ordersService.updateById(new Orders().setId(ordersServiceById.getId())
                .setCancelAt(LocalDateTime.now())
                .setPayStatus(PayStatusEnum.CANCEL.getStatus()));
        return Response.ok("取消成功");
    }

    /**
     * 订单详情
     *
     * @param code 订单号
     * @return response
     */
    @GetMapping("/detail")
    public Response orderDetail(@NotBlank(message = "订单编号不能为空") String code) {
        Integer userId = SecurityUtils.getUserId();
        Orders ordersServiceByCode = ordersService.getOrderByCode(code);
        Assert.notNull(ordersServiceByCode, "该订单不存在");
        Assert.isTrue(Objects.equals(userId, ordersServiceByCode.getUserId()), "非法操作");
        List<OrdersDetail> detailList = ordersDetailService.listByOrderId(ordersServiceByCode.getId());
        OrdersVo map = mapperFacade.map(ordersServiceByCode, OrdersVo.class);
        map.setPayStatusTitle(PayStatusEnum.getDescFromStatus(ordersServiceByCode.getPayStatus()));
        wrapDetail(map, detailList);
        return Response.ok(map);
    }

    /**
     * 封装订单详情
     *
     * @param map        订单实体
     * @param detailList 订单详情List
     */
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

    /**
     * 创建订单
     *
     * @param ordersVo 订单实体
     * @return response
     */
    @PostMapping("/create")
    public Response orderCreate(@RequestBody @Validated OrdersVo ordersVo) {
        Integer userId = SecurityUtils.getUserId();
        List<CartsVo> cartsList = ordersVo.getCartsList();
        Assert.isTrue(CollectionUtils.isNotEmpty(cartsList), "购物车商品为空");
        // 订单
        Orders orders = new Orders().setCode(IdUtils.orderId())
                .setUserId(userId)
                .setExpiredAt(LocalDateTime.now().plusHours(12L))
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId)
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
        // 订单详情
        List<OrdersDetail> details = cartsList.stream().map(item -> new OrdersDetail().setCourseId(item.getCourseId())
                .setDiscountPrice(item.getDiscountPrice())
                .setIsDiscount(item.getIsDiscount())
                .setImg(item.getImg())
                .setTitle(item.getTitle())
                .setPrice(item.getPrice())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId)).collect(Collectors.toList());
        // 总金额
        BigDecimal cost = details.stream().map(item -> {
            if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                return item.getDiscountPrice();
            }
            return item.getPrice();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        orders.setCost(cost);
        // 删除购物车元素
        Set<Integer> cartsIds = cartsList.stream().map(CartsVo::getId).collect(Collectors.toSet());
        ordersComponent.createOrder(orders, details, cartsIds);
        return Response.ok(ImmutableMap.of("code", orders.getCode()));
    }

    /**
     * 支付订单
     *
     * @param ordersVo 订单实体
     * @return response
     */
    @PostMapping("/pay")
    public Response orderPay(@RequestBody OrdersVo ordersVo) {
        Integer userId = SecurityUtils.getUserId();
        String code = ordersVo.getCode();
        Assert.notBlank(code, "订单编号不能为空");
        Integer payType = ordersVo.getPayType();
        Assert.isTrue(PayTypeEnum.containsStatus(payType), "请选择正确的支付方式，余额、支付宝、微信");
        Orders orderByCode = ordersService.getOrderByCode(code);
        Assert.notNull(orderByCode, "该订单不存在");
        Assert.isFalse(PayStatusEnum.PAY.equalsStatus(orderByCode.getPayStatus()), "该订单已完成");
        Assert.isTrue(Objects.equals(userId, orderByCode.getUserId()), "非法操作");
        Assert.isFalse(PayStatusEnum.CANCEL.equalsStatus(orderByCode.getPayStatus()), "该订单已取消");

        // 更新订单表
        Orders updateOrders = new Orders().setId(orderByCode.getId()).setPayAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId)
                .setPayStatus(PayStatusEnum.PAY.getStatus()).setPayType(payType);

        // 计算订单金额
        List<OrdersDetail> detailList = ordersDetailService.listByOrderId(orderByCode.getId());
        BigDecimal cost = detailList.stream().map(item -> {
            if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                return item.getDiscountPrice();
            }
            return item.getPrice();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        Assert.equals(cost, orderByCode.getCost(), "订单异常支付失败,请联系管理员");

        //recharge 封装消费信息
        Recharges recharges = null;
        if (PayTypeEnum.BALANCE.equalsStatus(payType)) {
            recharges = new Recharges().setAmount(cost)
                    .setPayType(payType)
                    .setActionType(RechargeTypeEnum.OUT.getType())
                    .setUserId(userId).setRemark("订单支出，订单号：" + code)
                    .setCreatedBy(userId).setCreatedAt(LocalDateTime.now());
        }
        // bill 封装账单信息
        List<Bills> billsList = detailList.stream().map(item -> {
            Bills bills = new Bills().setPayType(payType).setCourseId(item.getCourseId())
                    .setOrderCode(code).setTitle(item.getTitle()).setUserId(userId)
                    .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
            if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                return bills.setCost(item.getDiscountPrice());
            }
            return bills.setCost(item.getPrice());
        }).collect(Collectors.toList());

        //user_course 封装用户课程信息
        List<UserCourse> userCourseList = detailList.stream()
                .map(item -> {
                    UserCourse userCourse = new UserCourse().setCourseId(item.getCourseId()).setUserId(userId)
                            .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
                    if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                        return userCourse.setCost(item.getDiscountPrice());
                    }
                    return userCourse.setCost(item.getPrice());
                }).collect(Collectors.toList());
        // course learn_persons
        Set<Integer> courseIds = detailList.stream().map(OrdersDetail::getCourseId).collect(Collectors.toSet());

        ordersComponent.pay(updateOrders, recharges, billsList, userCourseList, courseIds);
        return Response.ok("支付成功");
    }
}