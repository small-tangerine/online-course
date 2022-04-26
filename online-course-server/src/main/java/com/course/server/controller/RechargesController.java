package com.course.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Recharges;
import com.course.api.vo.server.RechargesVo;
import com.course.commons.enums.PayTypeEnum;
import com.course.commons.enums.RechargeTypeEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.MathUtils;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.RechargesService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 充值消费
 *
 * @since 2022-03-04
 */
@RequestMapping("/recharge")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class RechargesController {
    private final RechargesService rechargesService;
    private final MapperFacade mapperFacade;

    /**
     * 充值消费列表
     *
     * @param page 页码
     * @param size 页大小
     * @return response
     */
    @GetMapping("/list")
    public Response rechargeList(Integer page, Integer size) {
        LambdaQueryWrapper<Recharges> query = Wrappers.lambdaQuery();
        query.eq(Recharges::getUserId, SecurityUtils.getUserId())
                .orderByDesc(Recharges::getId);
        Paging<Recharges> paging = new Paging<>(page, size);
        rechargesService.page(paging, query);
        paging.convert(item -> {
            RechargesVo map = mapperFacade.map(item, RechargesVo.class);
            map.setActionTypeTitle(RechargeTypeEnum.getDescFromType(item.getActionType()));
            return map;
        });
        // 统计总费用
        BigDecimal cost = totalCost(SecurityUtils.getUserId());
        paging.setExtra(ImmutableMap.of("cost", cost));
        return Response.ok(paging);
    }

    /**
     * 充值
     *
     * @param rechargesVo 实体
     * @return response
     */
    @PostMapping("/create")
    public Response RechargeCreate(@RequestBody @Validated RechargesVo rechargesVo) {
        Integer userId = SecurityUtils.getUserId();
        String payType = PayTypeEnum.getNameFromType(rechargesVo.getPayType());
        Assert.notBlank(payType, "请选择正确的支付类型");
        Assert.isTrue(MathUtils.greaterThanOrEqual(rechargesVo.getAmount(), BigDecimal.ONE)
                && MathUtils.lessThanOrEqual(rechargesVo.getAmount(), new BigDecimal(5000)), "充值金额必须在1-5000之间");
        Recharges recharges = new Recharges().setUserId(userId)
                .setActionType(RechargeTypeEnum.IN.getType())
                .setAmount(rechargesVo.getAmount())
                .setPayType(rechargesVo.getPayType())
                .setRemark(payType + "充值")
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
        rechargesService.save(recharges);
        return Response.ok("充值成功");
    }

    /**
     * 账号余额
     *
     * @return response
     */
    @GetMapping("/info")
    public Response RechargeInfo() {
        return Response.ok(ImmutableMap.of("cost", totalCost(SecurityUtils.getUserId())));
    }

    private BigDecimal totalCost(Integer userId) {
        LambdaQueryWrapper<Recharges> query = Wrappers.lambdaQuery();
        query.eq(Recharges::getUserId, userId)
                .select(Recharges::getId, Recharges::getActionType, Recharges::getAmount);
        List<Recharges> list = rechargesService.list(query);
        if (CollectionUtils.isEmpty(list)) {
            return BigDecimal.ZERO;
        }
        return list.stream().map(item -> {
            if (RechargeTypeEnum.OUT.equalsType(item.getActionType())) {
                return BigDecimal.ZERO.subtract(item.getAmount());
            }
            return item.getAmount();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
