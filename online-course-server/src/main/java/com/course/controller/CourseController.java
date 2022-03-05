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

import javax.validation.constraints.NotNull;

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
        //TODO 课程列表
        return Response.ok();
    }

    @GetMapping("/detail")
    public Response courseDetail(@NotNull(message = "课程编号不能为空") Integer id) {
        // TODO 课程详情
        return Response.ok();
    }

    @GetMapping("/video")
    public Response courseVideo(@NotNull(message = "课程编号不能为空") Integer courseId,
                                @NotNull(message = "课程视频编号不能为空") Integer videoId) {
        // TODO 课程视频详情
        return Response.ok();
    }
}
