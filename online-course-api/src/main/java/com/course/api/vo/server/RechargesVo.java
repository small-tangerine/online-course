package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 充值、消费记录
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class RechargesVo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 类型 1充值 0消费
     */
    private Integer actionType;
    private String actionTypeTitle;

    /**
     * 支付类型 1余额支付 2支付宝 3微信
     */
    @NotNull(message = "请选择支付类型")
    private Integer payType;
    private String payTypeTitle;

    /**
     * 金额
     */
    @Min(value = 1,message = "充值金额不能小于1")
    @Max(value = 5000,message = "充值金额不能大于5000")
    @NotNull(message = "请输入要充值的金额")
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

}
