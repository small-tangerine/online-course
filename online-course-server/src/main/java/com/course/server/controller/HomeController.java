package com.course.server.controller;

import com.course.api.entity.Course;
import com.course.api.entity.CourseCategory;
import com.course.api.entity.HomeRecommend;
import com.course.api.enums.RecommendTypeEnum;
import com.course.api.vo.server.CourseListVo;
import com.course.commons.model.Response;
import com.course.service.service.CourseCategoryService;
import com.course.service.service.CourseService;
import com.course.service.service.HomeRecommendService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 首页
 *
 * @since 2022-03-05
 */
@RequestMapping("/home")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class HomeController {
    private final CourseService courseService;
    private final CourseCategoryService courseCategoryService;
    private final HomeRecommendService homeRecommendService;
    private final MapperFacade mapperFacade;

    /**
     * 首页信息
     * @return
     */
    @GetMapping("/recommend")
    public Response homeRecommend() {
        List<HomeRecommend> list = homeRecommendService.list();
        Map<Integer, List<HomeRecommend>> collectMap = list.stream().collect(Collectors.groupingBy(HomeRecommend::getType));
        if (MapUtils.isEmpty(collectMap)) {
            return Response.ok(ImmutableMap.of("recommend", Collections.emptyList(),
                    "new", Collections.emptyList()));
        }
        // 实战推荐
        List<HomeRecommend> recommends = collectMap.get(RecommendTypeEnum.RECOMMEND.getType());
        // 新上好课
        List<HomeRecommend> news = collectMap.get(RecommendTypeEnum.NEW.getType());
        Set<Integer> courseIds = Sets.newHashSet();
        Set<Integer> recommendCourseIds = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(recommends)) {
            recommendCourseIds = recommends.stream().map(HomeRecommend::getCourseId).collect(Collectors.toSet());
            if (CollectionUtils.isNotEmpty(recommendCourseIds)) {
                courseIds.addAll(recommendCourseIds);
            }
        }
        Set<Integer> newsCourseIds = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(news)) {
            newsCourseIds = news.stream().map(HomeRecommend::getCourseId).collect(Collectors.toSet());
            if (CollectionUtils.isNotEmpty(newsCourseIds)) {
                courseIds.addAll(newsCourseIds);
            }
        }
        Map<Integer, List<CourseCategory>> courseCategoryMap = courseCategoryService.findMapByCourseIds(courseIds);
        Map<Integer, Course> courseMap = courseService.findMapByIds(courseIds);
        List<CourseListVo> recommendCourseList = wrapHomeRecommend(recommendCourseIds, courseCategoryMap, courseMap);
        List<CourseListVo> newCourseList = wrapHomeRecommend(newsCourseIds, courseCategoryMap, courseMap);

        return Response.ok(ImmutableMap.of("recommend", recommendCourseList,
                "new", newCourseList));
    }

    private List<CourseListVo> wrapHomeRecommend(Set<Integer> courseIds, Map<Integer, List<CourseCategory>> courseCategoryMap, Map<Integer, Course> courseMap) {
        return courseIds.stream()
                .map(item -> {
                    Course course = courseMap.get(item);
                    if (Objects.nonNull(course)) {
                        CourseListVo map = mapperFacade.map(course, CourseListVo.class);
                        List<CourseCategory> courseCategories = courseCategoryMap.get(item);
                        if (CollectionUtils.isNotEmpty(courseCategories)) {
                            List<String> labels = courseCategories.stream().map(CourseCategory::getTitle).distinct().collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(labels)) {
                                map.setLabels(labels);
                            }
                        }
                        return map;
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
