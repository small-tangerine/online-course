package com.course.api.vo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 首页vo
 *
 * @author panguangming
 * @since 2022-03-12
 */
@Data
@Accessors(chain = true)
public class IndexVo {
    private List<Integer> dayData;
    private List<Integer> totalData;
    private List<BigDecimal> dayCostData;
    private List<BigDecimal> totalCostData;
    private String addTitle;
    private String totalTitle;

    private Integer dayUser;
    private Integer totalUser;
    private Integer dayCourse;
    private Integer totalCourse;
    private BigDecimal dayCost;
    private BigDecimal totalCost;
    private BigDecimal thisCost;
    private BigDecimal lastCost;
    private Integer dayOrder;
    private Integer totalOrder;
}
