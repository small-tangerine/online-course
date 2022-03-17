package com.course.component.component;

import com.course.service.service.CourseCategoryService;
import com.course.service.service.CourseService;
import com.course.service.service.HomeRecommendService;
import com.course.service.service.TeacherCourseService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserCourseService;
import com.course.service.service.UserCourseVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 课程
 *
 * @author panguangming
 * @since 2022-03-13
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CourseVideoComponent {
    private final CourseService courseService;
    private final CourseCategoryService courseCategoryService;
    private final TeachersService teachersService;
    private final TeacherCourseService teacherCourseService;
    private final HomeRecommendService homeRecommendService;
    private final UserCourseService userCourseService;
    private final UserCourseVideoService userCourseVideoService;

}
