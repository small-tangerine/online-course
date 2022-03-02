package com.course.controller;

import com.course.commons.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author panguangming
 * @since 2022-03-02
 */
@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AccountController {

    @GetMapping("/login")
    public Response login() {
        return Response.ok();
    }
}
