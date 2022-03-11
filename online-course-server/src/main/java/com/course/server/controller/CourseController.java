package com.course.server.controller;

import com.course.api.entity.Course;
import com.course.api.entity.TeacherCourse;
import com.course.api.entity.Teachers;
import com.course.api.entity.UserCourse;
import com.course.api.vo.server.CourseListVo;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.CourseService;
import com.course.service.service.TeacherCourseService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserCourseService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final TeacherCourseService teacherCourseService;
    private final TeachersService teachersService;
    private final MapperFacade mapperFacade;
    private final UserCourseService userCourseService;

    @GetMapping("/list")
    public Response courseList(Integer page, Integer size, String keyword, Integer sort,
                               @NotNull(message = "一级分类编号不能为空") Integer category,
                               @NotNull(message = "二级分类编号不能为空") Integer label, Integer type) {
        Map<String, Object> paramsMap = Maps.newHashMapWithExpectedSize(5);
        paramsMap.put("type", type);
        paramsMap.put("sort", sort);
        paramsMap.put("label", label);
        paramsMap.put("category", category);
        if (StringUtils.isNotBlank(keyword)) {
            paramsMap.put("keyword", StringUtils.trim(keyword));
        }
        Paging<Course> paging = new Paging<>(page, size);
        courseService.listByParamsMap(paging, paramsMap);
        Set<Integer> courseIds = paging.getItems().stream().map(Course::getId).collect(Collectors.toSet());
        List<TeacherCourse> teacherCourseList = teacherCourseService.listByCourseIds(courseIds);
        Map<Integer, Integer> courseTeacherMap = teacherCourseList.stream().collect(Collectors.toMap(TeacherCourse::getCourseId, TeacherCourse::getTeacherId));
        Set<Integer> teacherIds = teacherCourseList.stream().map(TeacherCourse::getTeacherId).collect(Collectors.toSet());
        Map<Integer, Teachers> teachersMap = teachersService.findMapByTeacherIds(teacherIds);
        // 课程是否已购买
        Integer userId = SecurityUtils.getUserId();
        Map<Integer, UserCourse> userCourseMap = userCourseService.findMapByCourseIds(courseIds, userId);
        paging.convert(item -> {
            CourseListVo map = mapperFacade.map(item, CourseListVo.class);
            Integer teacherId = courseTeacherMap.get(item.getId());
            if (Objects.nonNull(teacherId)) {
                Teachers teachers = teachersMap.get(teacherId);
                if (Objects.nonNull(teachers)) {
                    map.setTeacher(teachers);
                }
            }
            UserCourse userCourse = userCourseMap.get(item.getId());
            map.setIsBuy(Objects.nonNull(userCourse) ? YesOrNoEnum.YES.getValue() : YesOrNoEnum.NO.getValue());
            return map;
        });
        return Response.ok(paging);
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

    @GetMapping("/user-course")
    public Response userCourse(Integer page,Integer type) {
        Paging<Course> paging= new Paging<>(page, 5);
        Integer userId = SecurityUtils.getUserId();
       courseService.listByUserCourse(paging,type, userId);
        return Response.ok(paging);
    }
}
