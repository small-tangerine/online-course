package com.course.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Category;
import com.course.api.vo.server.CategoryVo;
import com.course.commons.model.Response;
import com.course.component.cache.CategoryCache;
import com.course.service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private final CategoryService categoryService;
    private final MapperFacade mapperFacade;

    @GetMapping("/list")
    public Response categoryList() {
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.select(Category::getId, Category::getParentId, Category::getTitle, Category::getDisplayOrder);
        List<Category> list = categoryService.list(query);
        Map<Integer, Category> categoryMap = list.stream().collect(Collectors.toMap(Category::getId, Function.identity()));
        list.removeIf(item -> Objects.equals(item.getParentId(), 0));
        List<CategoryVo> categoryVoList = list.stream().map(item -> {
            CategoryVo map = mapperFacade.map(item, CategoryVo.class);
            Category category = categoryMap.get(item.getParentId());
            map.setParentTitle(category.getTitle())
                    .setParentDisplayOrder(category.getDisplayOrder());
            return map;
        }).collect(Collectors.toList());
        return Response.ok(categoryVoList);
    }
}
