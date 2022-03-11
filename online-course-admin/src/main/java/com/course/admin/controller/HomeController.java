package com.course.admin.controller;

import com.course.commons.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/index")
    public Response homeIndex(Integer type) {
        return Response.ok();
    }
}
