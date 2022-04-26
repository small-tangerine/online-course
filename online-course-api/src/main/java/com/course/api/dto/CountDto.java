package com.course.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 计数
 *
 * @since 2022-03-12
 */
@Data
@Accessors(chain = true)
public class CountDto {
    private Integer index;
    private LocalDate localDate;
    private Integer id;

    private Integer count;
    private BigDecimal cost;
}
