package com.course.server.controller;

import com.course.commons.model.Response;
import com.course.component.cache.CategoryCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类
 *
 * @author panguangming
 * @since 2022-03-04
 */
@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoryController {
    private final CategoryCache categoryCache;

    @GetMapping("/list")
    public Response categoryList() {
        return Response.ok(categoryCache.getCategoryVoList());
    }
}
