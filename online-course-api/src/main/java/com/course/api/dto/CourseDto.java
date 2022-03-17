package com.course.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程Dto
 *
 * @author panguangming
 * @since 2022-03-12
 */
@Data
@Accessors(chain = true)
public class CourseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer type;

    private Integer count;
}
