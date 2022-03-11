package com.course.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Carts;
import com.course.api.entity.Course;
import com.course.api.vo.server.CartsVo;
import com.course.commons.enums.CourseTypeEnum;
import com.course.commons.enums.StatusEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.CartsService;
import com.course.service.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 购物车
 *
 * @author panguangming
 * @since 2022-03-04
 */
@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CartsController {
    private final CartsService cartsService;
    private final MapperFacade mapperFacade;
    private final CourseService courseService;

    @GetMapping("/list")
    public Response cartsList(Integer page, Integer size) {
        LambdaQueryWrapper<Carts> query = Wrappers.lambdaQuery();
        query.orderByDesc(Carts::getId);
        Paging<Carts> paging = new Paging<>(page, 100);
        cartsService.page(paging, query);
        paging.convert(item -> mapperFacade.map(item, CartsVo.class));
        return Response.ok(paging);
    }

    @PostMapping("/delete/{id}")
    public Response cartsDelete(@PathVariable("id") Integer id) {
        Integer userId = SecurityUtils.getUserId();
        Carts carts = cartsService.getById(id);
        Assert.notNull(carts, "无效的购物车商品");
        Assert.isTrue(Objects.equals(userId, carts.getUserId()), "非法操作");
        cartsService.removeById(id);
        return ResponseHelper.deleteSuccess();
    }

    @PostMapping("/create")
    public Response cartsCreate(@RequestBody CartsVo cartsVo) {
        Integer userId = SecurityUtils.getUserId();
        Integer courseId = cartsVo.getCourseId();
        Assert.notNull(courseId, "无效的课程");
        // 课程是否有效 是否付费课程
        Course course = courseService.getById(courseId);
        Assert.notNull(course, "课程不存在");
        Assert.isTrue(StatusEnum.SUCCESS.equalsStatus(course.getAuditStatus()),
                "课程不存在");
        Assert.isTrue(CourseTypeEnum.UN_FREE.equalsStatus(course.getType()), "该课程是免费课程,无需购买");
        // 校验是否已经在购物车了
        Carts carts = cartsService.getByUserIdAndCourseId(userId, courseId);
        Assert.isNull(carts, "当前课程已添加到购物车，无需重复添加");
        Carts createCarts = new Carts().setUserId(userId)
                .setCourseId(courseId)
                .setPrice(course.getPrice())
                .setDiscountPrice(course.getDiscountPrice())
                .setIsDiscount(course.getIsDiscount())
                .setImg(course.getBanner())
                .setTitle(course.getTitle())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(userId);
        cartsService.save(createCarts);
        return Response.ok("添加成功");
    }

}
