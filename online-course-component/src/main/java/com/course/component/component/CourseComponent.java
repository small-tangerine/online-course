package com.course.component.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Course;
import com.course.api.entity.CourseCategory;
import com.course.api.entity.HomeRecommend;
import com.course.api.entity.TeacherCourse;
import com.course.api.entity.Teachers;
import com.course.api.entity.UserCourse;
import com.course.api.entity.UserCourseVideo;
import com.course.service.service.CourseCategoryService;
import com.course.service.service.CourseService;
import com.course.service.service.HomeRecommendService;
import com.course.service.service.TeacherCourseService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserCourseService;
import com.course.service.service.UserCourseVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 课程
 *
 * @author panguangming
 * @since 2022-03-13
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CourseComponent {
    private final CourseService courseService;
    private final CourseCategoryService courseCategoryService;
    private final TeachersService teachersService;
    private final TeacherCourseService teacherCourseService;
    private final HomeRecommendService homeRecommendService;
    private final UserCourseService userCourseService;
    private final UserCourseVideoService userCourseVideoService;

    @Transactional(rollbackFor = Exception.class)
    public void createCourse(Course course, Teachers byUserId, TeacherCourse teacherCourse,
                             List<CourseCategory> courseCategoryList, HomeRecommend homeRecommend) {
        courseService.save(course);
        if (Objects.isNull(byUserId.getId())) {
            teachersService.save(byUserId);
        }
        if (Objects.nonNull(teacherCourse)) {
            teacherCourse.setCourseId(course.getId()).setTeacherId(byUserId.getId());
            teacherCourseService.save(teacherCourse);
        }
        if (CollectionUtils.isNotEmpty(courseCategoryList)) {
            courseCategoryList.forEach(item -> item.setCourseId(course.getId()));
            courseCategoryService.saveBatch(courseCategoryList);
        }
        if (Objects.nonNull(homeRecommend)) {
            homeRecommend.setCourseId(course.getId());
            homeRecommendService.save(homeRecommend);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        courseService.removeById(id);

        LambdaQueryWrapper<CourseCategory> query = Wrappers.lambdaQuery();
        query.eq(CourseCategory::getCourseId, id);
        courseCategoryService.remove(query);

        LambdaQueryWrapper<TeacherCourse> queryTeacherCourse = Wrappers.lambdaQuery();
        queryTeacherCourse.eq(TeacherCourse::getCourseId, id);
        teacherCourseService.remove(queryTeacherCourse);

        LambdaQueryWrapper<HomeRecommend> queryHomeRecommend = Wrappers.lambdaQuery();
        queryHomeRecommend.eq(HomeRecommend::getCourseId, id);
        homeRecommendService.remove(queryHomeRecommend);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserVideo(UserCourseVideo userCourseVideo, UserCourse userCourse, Long length) {
        userCourseVideoService.saveOrUpdate(userCourseVideo);
        userCourseVideoService.increaseByColumn("cumulative_duration",
                Collections.singletonList(userCourseVideo.getId()), Math.toIntExact(length));
        if (Objects.isNull(userCourse.getId())) {
            userCourseService.save(userCourse);
            courseService.increaseByColumn("learn_persons",Collections.singletonList(userCourse.getCourseId()),1);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCourse(Course course, Teachers byUserId, List<CourseCategory> courseCategoryList,
                             HomeRecommend homeRecommend) {
        courseService.updateById(course);
        if (Objects.isNull(byUserId.getId())){
            teachersService.save(byUserId);
        }
        if (CollectionUtils.isNotEmpty(courseCategoryList)){
            LambdaQueryWrapper<CourseCategory> query=Wrappers.lambdaQuery();
            query.eq(CourseCategory::getCourseId,course.getId());
            courseCategoryService.remove(query);
            courseCategoryService.saveBatch(courseCategoryList);
        }
        if (Objects.nonNull(homeRecommend)){
            homeRecommendService.save(homeRecommend);
        }else {
            LambdaQueryWrapper<HomeRecommend> query=Wrappers.lambdaQuery();
            query.eq(HomeRecommend::getCourseId,course.getId());
            homeRecommendService.remove(query);
        }
    }
}
