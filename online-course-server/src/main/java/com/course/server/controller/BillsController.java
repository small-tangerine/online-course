package com.course.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Bills;
import com.course.api.vo.server.BillsVo;
import com.course.commons.enums.PayTypeEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.BillsService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账单
 *
 * @since 2022-03-04
 */
@RequestMapping("/bills")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class BillsController {
    private final BillsService billsService;
    private final MapperFacade mapperFacade;

    /**
     * 用户消费帐单列表
     *
     * @param page 页码
     * @param size 页大小
     * @return response
     */
    @GetMapping("/list")
    public Response billList(Integer page, Integer size) {
        LambdaQueryWrapper<Bills> query = Wrappers.lambdaQuery();
        query.eq(Bills::getUserId, SecurityUtils.getUserId())
                .orderByDesc(Bills::getId);
        Paging<Bills> paging = new Paging<>(page, size);
        billsService.page(paging, query);
        paging.convert(item -> {
            BillsVo map = mapperFacade.map(item, BillsVo.class);
            map.setPayTypeTitle(PayTypeEnum.getDescFromType(item.getPayType()));
            return map;
        });
        // 统计总消费金额
        BigDecimal cost = totalCost(SecurityUtils.getUserId());
        paging.setExtra(ImmutableMap.of("cost", cost));
        return Response.ok(paging);
    }

    private BigDecimal totalCost(Integer userId) {
        LambdaQueryWrapper<Bills> query = Wrappers.lambdaQuery();
        query.eq(Bills::getUserId, userId)
                .select(Bills::getCost);
        List<Bills> list = billsService.list(query);
        if (CollectionUtils.isEmpty(list)) {
            return BigDecimal.ZERO;
        }
        return list.stream().map(Bills::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
