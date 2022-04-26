package com.course.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Course;
import com.course.api.entity.CourseVideo;
import com.course.api.entity.TeacherCourse;
import com.course.api.entity.Teachers;
import com.course.api.entity.UserCourse;
import com.course.api.entity.UserCourseVideo;
import com.course.api.vo.server.CourseListVo;
import com.course.api.vo.server.CourseVideoListVo;
import com.course.api.vo.server.CourseVideoVo;
import com.course.api.vo.server.CourseVo;
import com.course.commons.enums.YesOrNoEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.MathUtils;
import com.course.commons.utils.StringUtil;
import com.course.component.component.CourseComponent;
import com.course.server.config.security.SecurityUtils;
import com.course.service.service.CourseService;
import com.course.service.service.CourseVideoService;
import com.course.service.service.TeacherCourseService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserCourseService;
import com.course.service.service.UserCourseVideoService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 课程
 *
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
    private final CourseVideoService courseVideoService;
    private final CourseComponent courseComponent;
    private final UserCourseVideoService userCourseVideoService;

    /**
     * 获取课程列表
     *
     * @param page     页码
     * @param size     页大小
     * @param keyword  关键词
     * @param sort     排序
     * @param category 分类
     * @param label    二级分类
     * @param type     类型
     * @return response
     */
    @GetMapping("/list")
    public Response courseList(Integer page, Integer size, String keyword, Integer sort,
                               @NotNull(message = "一级分类编号不能为空") Integer category,
                               @NotNull(message = "二级分类编号不能为空") Integer label, Integer type) {
        // 查询参数
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

    /**
     * 课程详情
     *
     * @param id    课程ID
     * @param alias 课程别名
     * @return response
     */
    @GetMapping("/detail")
    public Response courseDetail(Integer id, String alias) {
        Assert.isTrue(StringUtils.isNotBlank(alias)
                || Objects.nonNull(id), "课程编号或别名不能为空");
        Course course;
        // 是否根据ID查询
        if (Objects.nonNull(id)) {
            course = courseService.getById(id);
        } else if (StringUtils.isNotBlank(alias)) {
            LambdaQueryWrapper<Course> query = Wrappers.lambdaQuery();
            query.eq(Course::getAlias, StringUtils.trim(alias))
                    .last("limit 1");
            course = courseService.getOne(query);
        } else {
            return Response.ok();
        }
        if (Objects.isNull(course)) {
            return Response.ok();
        }
        // 讲师信息
        Teachers teachers = teachersService.getByCourseId(course.getId());
        CourseVo map = mapperFacade.map(course, CourseVo.class);
        wrapTips(map, course);
        if (Objects.nonNull(teachers)) {
            map.setTeacher(teachers);
        }
        wrapVideo(map, course);
        // 用户课程信息
        UserCourse byUserIdAndCourseId = userCourseService.getByUserIdAndCourseId(SecurityUtils.getUserId(), course.getId());
        map.setIsBuy(0);
        // 是否购买该课程或学习了免费课程
        if (Objects.nonNull(byUserIdAndCourseId)) {
            map.setIsBuy(1);
        }
        return Response.ok(map);
    }

    /**
     * 封装视频信息
     *
     * @param map    课程vo
     * @param course 课程实体
     */
    private void wrapVideo(CourseVo map, Course course) {
        List<CourseVideo> courseVideoList = courseVideoService.listByCourseId(course.getId());
        Set<Integer> videoIds = courseVideoList.stream().map(CourseVideo::getId).collect(Collectors.toSet());
        LambdaQueryWrapper<UserCourseVideo> query = Wrappers.lambdaQuery();
        Map<Integer, UserCourseVideo> userVideoMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(videoIds)) {
            query.eq(UserCourseVideo::getUserId, SecurityUtils.getUserId())
                    .in(UserCourseVideo::getVideoId, videoIds);
            userVideoMap = userCourseVideoService.list(query).stream().collect(Collectors.toMap(UserCourseVideo::getVideoId, Function.identity()));
        }

        Map<Integer, List<UserCourseVideo>> userCourseVideoMap = userCourseVideoService.findMapByCourseIdAndVideoIds(course.getId(), videoIds);
        AtomicLong length = new AtomicLong(0);
        Map<Integer, UserCourseVideo> finalUserVideoMap = userVideoMap;
        map.setVideos(courseVideoList.stream().map(item -> {
            CourseVideoListVo courseVideoListVo = mapperFacade.map(item, CourseVideoListVo.class);

            // 用户是否学习了当前视频
            UserCourseVideo userCourseVideo = finalUserVideoMap.get(item.getId());
            if (Objects.nonNull(userCourseVideo)) {
                courseVideoListVo.setIsFinish(userCourseVideo.getLearnStatus());
                if (Objects.equals(2, userCourseVideo.getLearnStatus())) {
                    courseVideoListVo.setCurrent(userCourseVideo.getLearnLength());
                }
            }
            length.addAndGet(item.getVideoLength());
            List<UserCourseVideo> userCourseVideos = userCourseVideoMap.get(item.getId());
            if (CollectionUtils.isNotEmpty(userCourseVideos)) {
                return courseVideoListVo.setPersonsTitle(MathUtils.getTenThousandOfANumber(userCourseVideos.size()) + "人学习");
            }

            return courseVideoListVo.setPersonsTitle("1人学习").setIsFinish(0);

        }).sorted(Comparator.comparingInt(CourseVideoListVo::getDisplayOrder)).collect(Collectors.toList()));
        map.setLength(length.get());
    }

    /**
     * 封装提示信息
     *
     * @param map    课程vo
     * @param course 课程实体
     */
    private void wrapTips(CourseVo map, Course course) {
        String learnWhat = course.getLearnWhat();
        AtomicInteger index = new AtomicInteger(1);
        if (StringUtils.isNotBlank(learnWhat)) {
            List<String> idList = StringUtil.getIdList(learnWhat, ";");
            List<String> collect = idList.stream().map(item -> index.getAndIncrement() + "、" + item.replaceAll("\r|\n|\t", "")).collect(Collectors.toList());
            map.setLearn(collect);
        }
        index.set(1);
        String tip = course.getTip();
        if (StringUtils.isNotBlank(tip)) {
            List<String> idList = StringUtil.getIdList(tip, ";");
            List<String> collect = idList.stream().map(item -> index.getAndIncrement() + "、" + item.replaceAll("\r|\n|\t", "")).collect(Collectors.toList());
            map.setTips(collect);
        }
    }

    /**
     * 获取课程视频
     *
     * @param videoId 视频ID
     * @return response
     */
    @GetMapping("/video")
    public Response courseVideo(Integer videoId) {
        // 课程视频详情
        CourseVideo byId = courseVideoService.getById(videoId);
        Assert.notNull(byId, "视频不存在");
        CourseVideoVo map = mapperFacade.map(byId, CourseVideoVo.class);
        return Response.ok(map);
    }

    /**
     * 更新视频学习进度
     *
     * @param courseVideoListVo 课程视频实体
     * @return response
     */
    @PostMapping("/video-set-current")
    public Response courseSetCurrentVideo(@RequestBody CourseVideoListVo courseVideoListVo) {
        //
        if (Objects.isNull(courseVideoListVo.getCurrent())) {
            courseVideoListVo.setCurrent(0L);
        }
        Integer id = courseVideoListVo.getId();
        CourseVideo byId = courseVideoService.getById(id);
        if (Objects.isNull(byId)) {
            return Response.ok();
        }
        // 用户是否添加了这门课程
        UserCourse userCourse = userCourseService.getByUserIdAndCourseId(SecurityUtils.getUserId(), byId.getCourseId());
        if (Objects.isNull(userCourse)) {
            userCourse = new UserCourse().setCourseId(byId.getCourseId()).setUserId(SecurityUtils.getUserId())
                    .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                    .setCost(BigDecimal.ZERO);
        }
        Long videoLength = byId.getVideoLength();
        boolean flag = videoLength.compareTo(courseVideoListVo.getCurrent()) <= 0;
        Long length = videoLength.compareTo(courseVideoListVo.getCurrent()) >= 0 ? courseVideoListVo.getCurrent() : videoLength;
        UserCourseVideo userCourseVideo = userCourseVideoService.getByUserIdAndVideoId(SecurityUtils.getUserId(), id);
        // 是否学习过该视频
        if (Objects.isNull(userCourseVideo)) {
            // 封装用户课程视频信息
            userCourseVideo = new UserCourseVideo().setUserId(SecurityUtils.getUserId()).setCourseId(byId.getCourseId())
                    .setVideoId(id).setLearnLength(length)
                    .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                    .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        } else {
            // 更新用户课程视频信息
            flag = videoLength.compareTo(userCourseVideo.getLearnLength()) <= 0;
            Long aLong = length.compareTo(userCourseVideo.getLearnLength()) >= 0 ? length : null;
            if (Objects.nonNull(aLong)) {
                userCourseVideo = new UserCourseVideo().setId(userCourseVideo.getId()).setLearnLength(aLong)
                        .setVideoId(id)
                        .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
            } else {
                userCourseVideo = new UserCourseVideo().setId(userCourseVideo.getId())
                        .setVideoId(id)
                        .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
            }
        }
        // 是否学完该视频
        if (flag) {
            userCourseVideo.setLearnStatus(YesOrNoEnum.YES.getValue());
        } else {
            userCourseVideo.setLearnStatus(2);
        }
        courseComponent.updateUserVideo(userCourseVideo, userCourse, 10L);
        return Response.ok();
    }

    /**
     * 获取用户学习的课程
     *
     * @param page 页码
     * @param type 类型
     * @return response
     */
    @GetMapping("/user-course")
    public Response userCourse(Integer page, Integer type) {
        Paging<Course> paging = new Paging<>(page, 5);
        Integer userId = SecurityUtils.getUserId();
        courseService.listByUserCourse(paging, type, userId);
        Set<Integer> courseId = paging.getItems().stream().map(Course::getId).collect(Collectors.toSet());
        Map<Integer, List<UserCourseVideo>> userCourseVideoMap = userCourseVideoService.findMapByUserIdAndCourseIds(SecurityUtils.getUserId(), courseId);
        paging.convert(item -> {
            CourseListVo map = mapperFacade.map(item, CourseListVo.class);
            map.setCurrent(0L);
            List<UserCourseVideo> userCourseVideos = userCourseVideoMap.get(item.getId());
            if (CollectionUtils.isNotEmpty(userCourseVideos)) {
                Long reduce = userCourseVideos.stream().map(UserCourseVideo::getLearnLength)
                        .reduce(0L, Long::sum);
                map.setCurrent(reduce);
            }
            return map;
        });
        return Response.ok(paging);
    }
}
