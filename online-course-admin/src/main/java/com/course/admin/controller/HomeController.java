package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.dto.CountDto;
import com.course.api.dto.CourseDto;
import com.course.api.entity.Course;
import com.course.api.entity.Orders;
import com.course.api.entity.OrdersDetail;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.admin.IndexVo;
import com.course.commons.enums.PayStatusEnum;
import com.course.commons.enums.StatusEnum;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Response;
import com.course.commons.utils.TimeUtils;
import com.course.service.service.CourseService;
import com.course.service.service.OrdersDetailService;
import com.course.service.service.OrdersService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 首页
 *
 * @author panguangming
 * @since 2022-03-11
 */
@RequestMapping("/home")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class HomeController {
    private final UserService userService;
    private final CourseService courseService;
    private final OrdersService ordersService;
    private final TeachersService teachersService;
    private final OrdersDetailService ordersDetailService;

    @GetMapping("/index")
    public Response homeIndex(Integer type) {
        Integer roleId = SecurityUtils.getRoleId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thisDayStart = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
        LocalDateTime dateEnd = LocalDateTime.of(now.toLocalDate(), LocalTime.MAX);
        LocalDateTime dateStart = LocalDateTime.of(now.toLocalDate()
                .minusDays(TimeUtils.getDayOfWeekValue(now.getDayOfWeek()) - 1L), LocalTime.MIN);

        IndexVo indexVo = new IndexVo();
        int total = 0;
        Map<Integer, List<CountDto>> countMap = Maps.newHashMap();
        BigDecimal totalCost;
        if (RoleTypeEnum.ADMIN.equalsStatus(roleId)) {
            // 用户
            int totalUser = userService.count();
            indexVo.setTotalUser(totalUser);
            LambdaQueryWrapper<User> queryUser = Wrappers.lambdaQuery();
            queryUser.lt(User::getCreatedAt, dateEnd)
                    .gt(User::getCreatedAt, thisDayStart);
            int dayUser = userService.count(queryUser);
            indexVo.setDayUser(dayUser);
            // 课程
            LambdaQueryWrapper<Course> queryCourse = Wrappers.lambdaQuery();
            queryCourse.eq(Course::getAuditStatus, StatusEnum.SUCCESS.getStatus());
            int totalCourse = courseService.count(queryCourse);
            indexVo.setTotalCourse(totalCourse);
            queryCourse.lt(Course::getCreatedAt, dateEnd)
                    .gt(Course::getCreatedAt, thisDayStart);
            int dayCourse = courseService.count(queryCourse);
            indexVo.setDayCourse(dayCourse);

            // 订单
            List<Orders> totalOrdersList = ordersService.list();
            indexVo.setTotalOrder(totalOrdersList.size());
            LambdaQueryWrapper<Orders> queryOrders = Wrappers.lambdaQuery();
            queryOrders.lt(Orders::getCreatedAt, dateEnd)
                    .gt(Orders::getCreatedAt, thisDayStart);
            List<Orders> thisDayOrderList = ordersService.list(queryOrders);
            indexVo.setDayOrder(thisDayOrderList.size());

            // 销售额
            totalCost = totalOrdersList.stream().map(item -> {
                if (PayStatusEnum.PAY.equalsStatus(item.getPayStatus())) {
                    return item.getCost();
                }
                return BigDecimal.ZERO;
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
            indexVo.setTotalCost(totalCost);
            BigDecimal thisDayCost = thisDayOrderList.stream().map(item -> {
                if (PayStatusEnum.PAY.equalsStatus(item.getPayStatus())) {
                    return item.getCost();
                }
                return BigDecimal.ZERO;
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
            indexVo.setDayCost(thisDayCost);

            // 用户
            if ((Objects.isNull(type) || Objects.equals(0, type))) {
                total = totalUser;
                LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
                query.lt(User::getCreatedAt, dateEnd)
                        .gt(User::getCreatedAt, dateStart);
                List<User> list = userService.list(query);
                countMap = list.stream().map(item ->
                        new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek())))
                        .collect(Collectors.groupingBy(CountDto::getIndex));
                indexVo.setAddTitle("新增用户").setTotalTitle("累计用户数");
            }
            // 课程
            if (Objects.equals(1, type)) {
                total = totalCourse;
                LambdaQueryWrapper<Course> query = Wrappers.lambdaQuery();
                query.eq(Course::getAuditStatus, StatusEnum.SUCCESS.getStatus())
                        .lt(Course::getCreatedAt, dateEnd)
                        .gt(Course::getCreatedAt, dateStart);
                List<Course> list = courseService.list(query);
                countMap = list.stream().map(item ->
                        new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek())))
                        .collect(Collectors.groupingBy(CountDto::getIndex));
                indexVo.setAddTitle("新增课程").setTotalTitle("累计课程数");
            }
            // 销售额
            if (Objects.equals(2, type)) {
                LambdaQueryWrapper<Orders> query = Wrappers.lambdaQuery();
                query.eq(Orders::getPayStatus, PayStatusEnum.PAY.getStatus())
                        .lt(Orders::getCreatedAt, dateEnd)
                        .gt(Orders::getCreatedAt, dateStart);
                List<Orders> list = ordersService.list(query);
                countMap = list.stream().map(item ->
                        new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek()))
                                .setCost(item.getCost()))
                        .collect(Collectors.groupingBy(CountDto::getIndex));
                indexVo.setAddTitle("销售额").setTotalTitle("累计销售额");
            }
            // 订单
            if (Objects.equals(3, type)) {
                total = totalOrdersList.size();
                LambdaQueryWrapper<Orders> query = Wrappers.lambdaQuery();
                query.lt(Orders::getCreatedAt, dateEnd)
                        .gt(Orders::getCreatedAt, dateStart);
                List<Orders> list = ordersService.list(query);
                countMap = list.stream().map(item ->
                        new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek())))
                        .collect(Collectors.groupingBy(CountDto::getIndex));
                indexVo.setAddTitle("新增订单").setTotalTitle("累计订单量");
            }
        } else {
            Teachers byUserId = teachersService.getByUserId(SecurityUtils.getUserId());
            // 课程
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("teacherId", byUserId.getId());
            List<CourseDto> courseDtoList = courseService.countCourse(paramMap);
            Integer totalCourse = courseDtoList.stream().map(CourseDto::getCount).reduce(0, Integer::sum);
            indexVo.setTotalCourse(totalCourse);
            paramMap.put("dateStart", thisDayStart);
            paramMap.put("dateEnd", dateEnd);
            List<Course> dayCourseList = courseService.listByParamsMap(paramMap);
            Integer dayCourse = dayCourseList.size();
            indexVo.setDayCourse(dayCourse);

            // 订单
            paramMap.remove("dateStart");
            paramMap.remove("dateEnd");
            List<OrdersDetail> ordersDetailList = ordersDetailService.listByParamsMap(paramMap);
            List<Integer> totalOrdersList = ordersDetailList.stream().map(OrdersDetail::getOrderId).distinct().collect(Collectors.toList());
            indexVo.setTotalOrder(totalOrdersList.size());
            paramMap.put("dateStart", thisDayStart);
            paramMap.put("dateEnd", dateEnd);
            List<OrdersDetail> thisDayOrdersDetailList = ordersDetailService.listByParamsMap(paramMap);
            List<Integer> thisDayOrderList = thisDayOrdersDetailList.stream().map(OrdersDetail::getOrderId).distinct().collect(Collectors.toList());
            indexVo.setDayOrder(thisDayOrderList.size());

            // 销售额
            totalCost = ordersDetailList.stream().map(item -> {
                if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                    return item.getDiscountPrice();
                }
                return item.getPrice();
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
            indexVo.setTotalCost(totalCost);
            BigDecimal thisDayCost = thisDayOrdersDetailList.stream().map(item -> {
                if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                    return item.getDiscountPrice();
                }
                return item.getPrice();
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
            indexVo.setDayCost(thisDayCost);

            // 课程
            if (RoleTypeEnum.TEACHER.equalsStatus(roleId) || Objects.equals(1, type)) {
                total = totalCourse;
                paramMap.put("dateStart", dateStart);
                paramMap.put("dateEnd", dateEnd);
                List<Course> list = courseService.listByParamsMap(paramMap);
                countMap = list.stream().map(item ->
                        new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek())))
                        .collect(Collectors.groupingBy(CountDto::getIndex));
                indexVo.setAddTitle("新增课程").setTotalTitle("累计课程数");
            }
            if (Objects.equals(2, type) || Objects.equals(3, type)) {
                paramMap.put("dateStart", thisDayStart);
                paramMap.put("dateEnd", dateEnd);
                List<OrdersDetail> detailList = ordersDetailService.listByParamsMap(paramMap);
                // 销售额
                if (Objects.equals(2, type)) {
                    countMap = detailList.stream().map(item -> {
                        CountDto countDto = new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek()));
                        if (YesOrNoEnum.YES.equalsStatus(item.getIsDiscount())) {
                            return countDto.setCost(item.getDiscountPrice());
                        }
                        return countDto.setCost(item.getPrice());
                    }).collect(Collectors.groupingBy(CountDto::getIndex));
                    indexVo.setAddTitle("销售额").setTotalTitle("累计销售额");
                }
                // 订单
                if (Objects.equals(3, type)) {
                    total = totalOrdersList.size();
                    Map<Integer, List<OrdersDetail>> collect = detailList.stream().collect(Collectors.groupingBy(OrdersDetail::getOrderId));
                    List<Orders> list = Lists.newLinkedList();
                    collect.forEach((key, value) -> {
                        Orders orders = new Orders().setId(key);
                        OrdersDetail ordersDetail = value.get(0);
                        orders.setCreatedAt(ordersDetail.getCreatedAt());
                        list.add(orders);
                    });
                    countMap = list.stream().map(item ->
                            new CountDto().setId(item.getId()).setLocalDate(item.getCreatedAt().toLocalDate())
                                    .setIndex(TimeUtils.getDayOfWeekValue(item.getCreatedAt().getDayOfWeek())))
                            .collect(Collectors.groupingBy(CountDto::getIndex));
                    indexVo.setAddTitle("新增订单").setTotalTitle("累计订单量");
                }
            }
        }

        List<CountDto> countDtoList = new LinkedList<>();
        for (int i = 1; i <= TimeUtils.getDayOfWeekValue(now.getDayOfWeek()); i++) {
            List<CountDto> countList = countMap.get(i);
            if (Objects.isNull(countList)) {
                countMap.put(i, Collections.emptyList());
            }
        }
        if (!Objects.equals(2, type)) {
            countMap.forEach((key, value) -> countDtoList.add(new CountDto().setIndex(key).setCount(value.size())));
            countDtoList.sort((a, b) -> b.getIndex() - a.getIndex());
            AtomicInteger finalTotal = new AtomicInteger(total);
            AtomicInteger lastCount = new AtomicInteger(0);
            List<CountDto> list = countDtoList.stream().map(item -> {
                finalTotal.set(finalTotal.get() - lastCount.get());
                CountDto countDto = new CountDto().setIndex(item.getIndex())
                        .setCount(finalTotal.get());
                lastCount.set(item.getCount());
                return countDto;
            }).sorted(Comparator.comparingInt(CountDto::getIndex)).collect(Collectors.toList());

            List<Integer> totalData = list.stream().map(CountDto::getCount).collect(Collectors.toList());

            countDtoList.sort(Comparator.comparingInt(CountDto::getIndex));
            List<Integer> dayData = countDtoList.stream().map(CountDto::getCount).collect(Collectors.toList());

            indexVo.setDayData(dayData).setTotalData(totalData);
        } else {
            countMap.forEach((key, value) -> {
                BigDecimal reduce = value.stream().map(CountDto::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
                countDtoList.add(new CountDto().setIndex(key).setCost(reduce));
            });

            countDtoList.sort((a, b) -> b.getIndex() - a.getIndex());
            indexVo.setLastCost(BigDecimal.ZERO).setThisCost(totalCost);
            List<CountDto> list = countDtoList.stream().map(item -> {
                BigDecimal cost = indexVo.getThisCost();
                indexVo.setThisCost(cost.subtract(indexVo.getLastCost()));
                CountDto countDto = new CountDto().setIndex(item.getIndex())
                        .setCost(indexVo.getThisCost());
                indexVo.setLastCost(item.getCost());
                return countDto;
            }).sorted(Comparator.comparingInt(CountDto::getIndex)).collect(Collectors.toList());

            List<BigDecimal> totalCostData = list.stream().map(CountDto::getCost).collect(Collectors.toList());

            countDtoList.sort(Comparator.comparingInt(CountDto::getIndex));
            List<BigDecimal> dayCostData = countDtoList.stream().map(CountDto::getCost).collect(Collectors.toList());
            indexVo.setDayCostData(dayCostData).setTotalCostData(totalCostData);
        }
        return Response.ok(indexVo);
    }
}
