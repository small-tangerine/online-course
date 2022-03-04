package com.course.controller;

import com.course.commons.model.Response;
import com.course.service.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程
 *
 * @author panguangming
 * @since 2022-03-05
 */
@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CourseController {
    private final CourseService courseService;
    private final MapperFacade mapperFacade;

    @GetMapping("/list")
    public Response courseList(Integer page, Integer size, String keyword, String sort,
                               Integer category, Integer label, Integer type) {
        return Response.ok();
    }
}
