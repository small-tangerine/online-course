package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.dto.CourseDto;
import com.course.api.entity.Category;
import com.course.api.entity.Course;
import com.course.api.entity.CourseCategory;
import com.course.api.entity.CourseVideo;
import com.course.api.entity.HomeRecommend;
import com.course.api.entity.TeacherCourse;
import com.course.api.entity.Teachers;
import com.course.api.entity.User;
import com.course.api.entity.UserCourse;
import com.course.api.entity.UserCourseVideo;
import com.course.api.enums.RecommendTypeEnum;
import com.course.api.enums.RoleTypeEnum;
import com.course.api.vo.admin.CourseDetailVo;
import com.course.api.vo.admin.CourseVideoVo;
import com.course.api.vo.admin.CourseVo;
import com.course.api.vo.admin.UserCourseVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.enums.CourseTypeEnum;
import com.course.commons.enums.StatusEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.IdUtils;
import com.course.commons.utils.ResponseHelper;
import com.course.commons.utils.StringUtil;
import com.course.component.component.CourseComponent;
import com.course.service.service.CategoryService;
import com.course.service.service.CourseCategoryService;
import com.course.service.service.CourseService;
import com.course.service.service.CourseVideoService;
import com.course.service.service.HomeRecommendService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserCourseService;
import com.course.service.service.UserCourseVideoService;
import com.course.service.service.UserService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 课程
 *
 * @author panguangming
 * @since 2022-03-12
 */
@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CourseController {
    private final TeachersService teachersService;
    private final CourseService courseService;
    private final UserCourseService userCourseService;
    private final UserCourseVideoService userCourseVideoService;
    private final MapperFacade mapperFacade;
    private final UserService userService;
    private final CourseVideoService courseVideoService;
    private final CourseCategoryService courseCategoryService;
    private final CategoryService categoryService;
    private final CourseComponent courseComponent;
    private final HomeRecommendService homeRecommendService;

    @GetMapping("/list")
    public Response courseList(Integer page, Integer pageSize, Integer auditStatus,
                               Integer type, Integer isDiscount, String keyword, Integer userId) {
        Integer roleId = SecurityUtils.getRoleId();
        Map<String, Object> paramsMap = Maps.newHashMapWithExpectedSize(5);
        if (RoleTypeEnum.TEACHER.equalsStatus(roleId) || Objects.nonNull(userId)) {
            if (Objects.isNull(userId)) {
                userId = SecurityUtils.getUserId();
            }
            Teachers byUserId = teachersService.getByUserId(userId);
            if (Objects.nonNull(byUserId)) {
                paramsMap.put("teacherId", byUserId.getId());
            }
        }
        paramsMap.put("type", type);
        paramsMap.put("auditStatus", auditStatus);
        paramsMap.put("isDiscount", isDiscount);
        if (StringUtils.isNotBlank(keyword)) {
            paramsMap.put("keyword", StringUtils.trim(keyword));
        }
        Paging<Course> paging = new Paging<>(page, pageSize);
        courseService.pagingByMap(paging, paramsMap);
        List<CourseDto> courseDto = courseService.countCourse(paramsMap);
        Map<Integer, Integer> map = courseDto.stream().collect(Collectors.toMap(CourseDto::getType, CourseDto::getCount));
        paging.setExtra(ImmutableMap.of("free", ObjectUtils.defaultIfNull(map.get(CourseTypeEnum.FREE.getValue()), 0),
                "unFree", ObjectUtils.defaultIfNull(map.get(CourseTypeEnum.UN_FREE.getValue()), 0)));
        return Response.ok(paging);
    }


    @GetMapping("/student-list")
    public Response courseUserList(Integer page, Integer pagesSize, String keyword, @NotNull(message = "课程编号不能为空") Integer courseId) {
        Course byId = courseService.getById(courseId);
        Assert.notNull(byId, "课程不存在");
        Paging<UserCourse> paging = new Paging<>(page, pagesSize);
        Map<String, Object> paramsMap = Maps.newHashMapWithExpectedSize(2);
        paramsMap.put("courseId", courseId);
        if (StringUtils.isNotBlank(keyword)) {
            paramsMap.put("keyword", StringUtils.trim(keyword));
        }
        userCourseService.listPagingByMap(paging, paramsMap);
        Set<Integer> userIds = paging.getItems().stream().map(UserCourse::getUserId).collect(Collectors.toSet());
        Map<Integer, User> mapByUserIds = userService.findMapByUserIds(userIds);
        Map<Integer, List<UserCourseVideo>> userCourseVideoMap = userCourseVideoService.findMapByUserIdsAndCourseId(userIds, courseId);
        BigDecimal cost = paging.getItems().stream().map(UserCourse::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        paging.setExtra(ImmutableMap.of("cost", cost));
        paging.convert(item -> {
            UserCourseVo map = mapperFacade.map(item, UserCourseVo.class);
            User user = mapByUserIds.get(item.getUserId());
            if (Objects.nonNull(user)) {
                map.setUserTitle(user.getNickname());
            }
            List<UserCourseVideo> userCourseVideos = userCourseVideoMap.get(item.getUserId());
            if (CollectionUtils.isNotEmpty(userCourseVideos)) {
                Long cumulative = userCourseVideos.stream().map(UserCourseVideo::getCumulativeDuration).reduce(0L, Long::sum);
                map.setLearnLength(cumulative);
                UserCourseVideo userCourseVideo = userCourseVideos.stream()
                        .max((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt())).orElse(null);
                if (Objects.nonNull(userCourseVideo)) {
                    map.setRecentAt(userCourseVideo.getUpdatedAt());
                }
            }
            map.setCourseTitle(byId.getTitle());
            return map;
        });
        return Response.ok(paging);
    }


    @GetMapping("/detail")
    public Response courseDetail(@NotNull(message = "课程编号不能为空") Integer id) {
        Course byId = courseService.getById(id);
        Assert.notNull(byId, "课程不存在");
        CourseDetailVo map = mapperFacade.map(byId, CourseDetailVo.class);
        map.setAuditStatusTitle(StatusEnum.getDescFromStatus(byId.getAuditStatus()))
                .setTypeTitle(CourseTypeEnum.getDescByValue(byId.getType()));
        LambdaQueryWrapper<CourseVideo> query = Wrappers.lambdaQuery();
        query.eq(CourseVideo::getCourseId, id);
        List<CourseVideo> courseVideoList = courseVideoService.list(query);
        map.setVideoCount(courseVideoList.size());
        if (CollectionUtils.isNotEmpty(courseVideoList)) {
            List<CourseVideoVo> collect = courseVideoList.stream().map(item -> {
                CourseVideoVo courseVideoVo = mapperFacade.map(item, CourseVideoVo.class);
                courseVideoVo.setAuditStatusTitle(StatusEnum.getDescFromStatus(item.getAuditStatus()));
                return courseVideoVo;
            }).collect(Collectors.toList());
            map.setVideo(collect);
        }
        Teachers byCourseId = teachersService.getByCourseId(id);
        if (Objects.nonNull(byCourseId)) {
            map.setTeacher(byCourseId);
        }
        Map<Integer, List<CourseCategory>> mapByCourseIds = courseCategoryService.findMapByCourseIds(Collections.singletonList(id));
        List<CourseCategory> courseCategories = mapByCourseIds.get(id);
        if (CollectionUtils.isNotEmpty(courseCategories)) {
            Set<String> collect = courseCategories.stream().map(CourseCategory::getTitle).collect(Collectors.toSet());
            map.setCategoryStr(collect);
        }
        return Response.ok(map);
    }

    @PostMapping("/create")
    public Response courseCreate(@RequestBody CourseVo courseVo) {
        Integer userId = SecurityUtils.getUserId();
        Course course = newCourse(courseVo);
        Teachers byUserId = teachersService.getByUserId(userId);
        if (Objects.isNull(byUserId)) {
            byUserId = new Teachers().setUserId(userId).setAvatar(CommonConstant.DEFAULT_AVATAR)
                    .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId).setName("管理员")
                    .setUpdatedBy(userId).setUpdatedAt(LocalDateTime.now())
                    .setJob("管理员");
        }
        // 关联讲师
        TeacherCourse teacherCourse = new TeacherCourse().setTeacherId(byUserId.getId()).setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
        // 关联分类
        Collection<Integer> categoryIds = courseVo.getCategoryIds();
        List<CourseCategory> courseCategoryList = null;
        if (CollectionUtils.isNotEmpty(categoryIds)) {
            List<Category> categories = categoryService.listByIds(categoryIds);
            Map<Integer, String> stringMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getTitle));
            courseCategoryList = categoryIds.stream().map(item -> {
                String title = stringMap.get(item);
                if (StringUtils.isNotBlank(title)) {
                    return new CourseCategory().setCategoryId(item).setTitle(title)
                            .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }

        // 是否首页推荐
        HomeRecommend homeRecommend = null;
        if (RecommendTypeEnum.containsStatus(courseVo.getRecommend())) {
            homeRecommend = new HomeRecommend().setType(courseVo.getType()).setCreatedBy(userId).setCreatedAt(LocalDateTime.now());
        }
        courseComponent.createCourse(course, byUserId, teacherCourse, courseCategoryList, homeRecommend);
        return Response.ok("课程创建成功");
    }

    private Course newCourse(CourseVo courseVo) {
        Course course = new Course().setTitle(courseVo.getTitle())
                .setType(courseVo.getType()).setIntroduction(courseVo.getIntroduction())
                .setBanner(courseVo.getBanner()).setBgImg(courseVo.getBgImg())
                .setIsFinish(courseVo.getIsFinish()).setUpdatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now());
        // 付费课程
        if (CourseTypeEnum.UN_FREE.equalsStatus(courseVo.getType())) {
            course.setIsDiscount(courseVo.getIsDiscount()).setDiscountPrice(courseVo.getDiscountPrice())
                    .setPrice(courseVo.getPrice());
        }

        // 须知
        if (CollectionUtils.isNotEmpty(courseVo.getTipList())) {
            String tips = courseVo.getTipList().stream().filter(StringUtils::isNotBlank)
                    .distinct().collect(Collectors.joining(";"));
            course.setTip(tips);
        } else {
            course.setTip(StringUtils.EMPTY);
        }
        // 能学什么
        if (CollectionUtils.isNotEmpty(courseVo.getLearnWhatList())) {
            String learnWhat = courseVo.getLearnWhatList().stream().filter(StringUtils::isNotBlank)
                    .distinct().collect(Collectors.joining(";"));
            course.setLearnWhat(learnWhat);
        } else {
            course.setLearnWhat(StringUtils.EMPTY);
        }

        if (Objects.nonNull(courseVo.getId())) {
            course.setId(courseVo.getId());
        } else {
            if (StringUtils.isBlank(courseVo.getAlias())) {
                course.setAlias(IdUtils.uuid());
            } else {
                Course alias = courseService.getByAlias(course.getAlias());
                Assert.isNull(alias, "该课程别名已存在");
            }
            course.setAuditStatus(StatusEnum.WAIT.getStatus())
                    .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId());
        }
        return course;
    }

    @PostMapping("/audit")
    public Response courseAudit(@RequestBody Course course) {
        Integer id = course.getId();
        Integer auditStatus = course.getAuditStatus();
        String auditNotice = course.getAuditNotice();
        Assert.notNull(id, "课程编号不能为空");
        Course byId = courseService.getById(id);
        Assert.isTrue(StatusEnum.WAIT.equalsStatus(byId.getAuditStatus()), "该课程不处于待审核状态");
        Assert.isTrue(StatusEnum.containsStatus(auditStatus), "审核决定不正确");
        Assert.isFalse(StringUtils.isBlank(auditNotice) && StatusEnum.FAIL.equalsStatus(auditStatus), "审核不通过原因不能为空");
        Course updatedBy = new Course().setId(id).setAuditStatus(auditStatus).setAuditNotice(auditNotice)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        courseService.updateById(updatedBy);
        return Response.ok("审核操作成功");
    }


    @PostMapping("/delete")
    public Response courseDelete(@RequestBody CourseVo courseVo) {
        Integer id = courseVo.getId();
        Assert.notNull(id, "课程编号不能为空");
        Course byId = courseService.getById(id);
        Assert.notNull(byId, "课程不存在");
        LambdaQueryWrapper<UserCourse> query = Wrappers.lambdaQuery();
        query.eq(UserCourse::getCourseId, id);
        int count = userCourseService.count(query);
        Assert.equals(0, count, "该课程已有学生在学习不允许被删除");
        courseComponent.delete(id);
        return ResponseHelper.deleteSuccess();
    }


    @GetMapping("/view")
    public Response courseView(@NotNull(message = "课程编号不能为空") Integer id) {
        Course byId = courseService.getById(id);
        Assert.notNull(byId, "课程不存在");
        CourseVo map = mapperFacade.map(byId, CourseVo.class);
        Map<Integer, List<CourseCategory>> mapByCourseIds = courseCategoryService.findMapByCourseIds(Collections.singletonList(id));
        List<CourseCategory> courseCategories = mapByCourseIds.get(id);
        if (CollectionUtils.isNotEmpty(courseCategories)) {
            Set<Integer> collect = courseCategories.stream().map(CourseCategory::getCategoryId).collect(Collectors.toSet());
            map.setCategoryIds(collect);
        }
        Collection<Integer> categoryIds = map.getCategoryIds();
        if (CollectionUtils.isNotEmpty(categoryIds)) {
            List<Integer> parentIds = categoryService.getParentById(categoryIds);
            if (CollectionUtils.isNotEmpty(parentIds)) {
                categoryIds.addAll(parentIds);

            }
        }
        List<String> idList = StringUtil.getIdList(byId.getLearnWhat(), ";");
        map.setLearnWhatList(idList);
        List<String> tips = StringUtil.getIdList(byId.getTip(), ";");
        map.setTipList(tips);
        HomeRecommend homeRecommend = homeRecommendService.getByCourseId(id);
        if (Objects.nonNull(homeRecommend)) {
            map.setRecommend(homeRecommend.getType());
        }
        return Response.ok(map);
    }



    @PostMapping("/update")
    public Response courseUpdate(@RequestBody CourseVo courseVo) {
        Assert.notNull(courseVo.getId(),"课程编号不能为空");
        Integer userId = SecurityUtils.getUserId();
        Course course = newCourse(courseVo);
        Teachers byUserId = teachersService.getByUserId(userId);
        if (Objects.isNull(byUserId)) {
            byUserId = new Teachers().setUserId(userId).setAvatar(CommonConstant.DEFAULT_AVATAR)
                    .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId).setName("管理员")
                    .setUpdatedBy(userId).setUpdatedAt(LocalDateTime.now())
                    .setJob("管理员");
        }
        // 关联分类
        Collection<Integer> categoryIds = courseVo.getCategoryIds();
        List<CourseCategory> courseCategoryList = null;
        if (CollectionUtils.isNotEmpty(categoryIds)) {
            List<Category> categories = categoryService.listByIds(categoryIds);
            Map<Integer, String> stringMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getTitle));
            courseCategoryList = categoryIds.stream().map(item -> {
                String title = stringMap.get(item);
                if (StringUtils.isNotBlank(title)) {
                    return new CourseCategory().setCategoryId(item).setTitle(title)
                            .setCourseId(courseVo.getId())
                            .setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }

        // 是否首页推荐
        HomeRecommend homeRecommend = null;
        if (RecommendTypeEnum.containsStatus(courseVo.getRecommend())) {
            HomeRecommend byCourseId = homeRecommendService.getByCourseIdAndType(courseVo.getId(),courseVo.getRecommend());
            if (Objects.isNull(byCourseId)){
                homeRecommend = new HomeRecommend()
                        .setCourseId(courseVo.getId())
                        .setType(courseVo.getType()).setCreatedBy(userId).setCreatedAt(LocalDateTime.now());
            }
        }
        course.setAuditStatus(StatusEnum.WAIT.getStatus())
                .setAuditNotice(StringUtils.EMPTY);
        courseComponent.updateCourse(course, byUserId, courseCategoryList, homeRecommend);
        return Response.ok("课程更新成功");
    }
}
