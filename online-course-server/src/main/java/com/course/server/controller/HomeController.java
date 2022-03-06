package com.course.server.controller;

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
 * 首页
 *
 * @author panguangming
 * @since 2022-03-05
 */
@RequestMapping("/home")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class HomeController {
    private final CourseService courseService;
    private final MapperFacade mapperFacade;

    @GetMapping("/recommend")
    public Response homeRecommend() {
        // TODO 首页推荐
        return Response.ok();
    }
}
