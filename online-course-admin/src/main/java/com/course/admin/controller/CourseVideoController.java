package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.CourseVideo;
import com.course.api.entity.User;
import com.course.api.entity.UserCourseVideo;
import com.course.api.vo.admin.CourseVideoVo;
import com.course.api.vo.admin.UserCourseVideoVo;
import com.course.commons.constant.CommonConstant;
import com.course.commons.enums.StatusEnum;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.FileSizeUtil;
import com.course.commons.utils.ResponseHelper;
import com.course.component.component.CourseVideoComponent;
import com.course.service.service.CategoryService;
import com.course.service.service.CourseCategoryService;
import com.course.service.service.CourseService;
import com.course.service.service.CourseVideoService;
import com.course.service.service.HomeRecommendService;
import com.course.service.service.TeachersService;
import com.course.service.service.UserCourseService;
import com.course.service.service.UserCourseVideoService;
import com.course.service.service.UserService;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 课程
 *
 * @since 2022-03-12
 */
@RequestMapping("/course-video")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CourseVideoController {
    private final TeachersService teachersService;
    private final CourseService courseService;
    private final UserCourseService userCourseService;
    private final UserCourseVideoService userCourseVideoService;
    private final MapperFacade mapperFacade;
    private final UserService userService;
    private final CourseVideoService courseVideoService;
    private final CourseCategoryService courseCategoryService;
    private final CategoryService categoryService;
    private final CourseVideoComponent courseVideoComponent;
    private final HomeRecommendService homeRecommendService;

    /**
     * 获取课程视频列表
     * @param page 页码
     * @param pageSize 页大小
     * @param auditStatus 审核状态
     * @param keyword 关键词
     * @param courseId 课程ID
     * @return response
     */
    @GetMapping("/list")
    public Response courseList(Integer page, Integer pageSize, Integer auditStatus, String keyword,
                               @NotNull(message = "课程编号不能为空") Integer courseId) {

        Map<String, Object> paramsMap = Maps.newHashMapWithExpectedSize(5);
        paramsMap.put("auditStatus", auditStatus);
        if (StringUtils.isNotBlank(keyword)) {
            paramsMap.put("keyword", StringUtils.trim(keyword));
        }
        paramsMap.put("courseId", courseId);
        Paging<CourseVideo> paging = new Paging<>(page, pageSize);
        courseVideoService.pagingByParamsMap(paging, paramsMap);
        Set<Integer> collect = paging.getItems().stream().map(CourseVideo::getId).collect(Collectors.toSet());
        Map<Integer, List<UserCourseVideo>> mapByCourseIdAndVideoIds = userCourseVideoService.findMapByCourseIdAndVideoIds(courseId, collect);
        paging.convert(item -> {
            CourseVideoVo map = mapperFacade.map(item, CourseVideoVo.class);
            map.setAuditStatusTitle(StatusEnum.getDescFromStatus(item.getAuditStatus()));
            List<UserCourseVideo> userCourseVideos = mapByCourseIdAndVideoIds.get(item.getId());
            map.setLearnPersons(0);
            if (CollectionUtils.isNotEmpty(userCourseVideos)) {
                map.setLearnPersons(userCourseVideos.size());
            }
            // 大小转成M
            map.setFileSizeTitle(FileSizeUtil.formatFileSize(item.getFileSize()));
            return map;
        });
        return Response.ok(paging);
    }

    /**
     * 课程视频学生列表
     *
     * @param page 页码
     * @param pagesSize 页大小
     * @param keyword 关键词
     * @param courseId 课程ID
     * @return response
     */
    @GetMapping("/student-list")
    public Response courseUserList(Integer page, Integer pagesSize, String keyword, @NotNull(message = "课程视频编号不能为空") Integer courseId) {
        CourseVideo byId = courseVideoService.getById(courseId);
        Assert.notNull(byId, "课程视频不存在");
        Paging<UserCourseVideo> paging = new Paging<>(page, pagesSize);
        Map<String, Object> paramsMap = Maps.newHashMapWithExpectedSize(2);
        paramsMap.put("courseId", courseId);
        if (StringUtils.isNotBlank(keyword)) {
            paramsMap.put("keyword", StringUtils.trim(keyword));
        }
        userCourseVideoService.listPagingByMap(paging, paramsMap);
        Set<Integer> userIds = paging.getItems().stream().map(UserCourseVideo::getUserId).collect(Collectors.toSet());
        Map<Integer, User> mapByUserIds = userService.findMapByUserIds(userIds);
        paging.convert(item -> {
            UserCourseVideoVo map = mapperFacade.map(item, UserCourseVideoVo.class);
            User user = mapByUserIds.get(item.getUserId());
            if (Objects.nonNull(user)) {
                map.setUserTitle(user.getNickname());
            }
            map.setVideoTitle(byId.getTitle());
            return map;
        });
        return Response.ok(paging);
    }

    /**
     * 新增课程视频
     * @param courseVo 视频实体
     * @return response
     */
    @PostMapping("/create")
    public Response courseCreate(@RequestBody @Validated CourseVideoVo courseVo) {
        Integer courseId = courseVo.getCourseId();
        Assert.notNull(courseId, "课程编号不能为空");

        CourseVideo map = mapperFacade.map(courseVo, CourseVideo.class);
        if (StringUtils.isBlank(courseVo.getThumbUrl())) {
            map.setThumbUrl(CommonConstant.DEFAULT_THUMB_URL);
        }
        // 视频信息
        map.setAuditNotice(StringUtils.EMPTY)
                .setAuditStatus(StatusEnum.WAIT.getStatus())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId())
                .setDisplayOrder(1);
        courseVideoService.save(map);
        return Response.ok("课程视频创建成功");
    }

    /**
     * 视频审核
     * @param course 视频实体
     * @return response
     */
    @PostMapping("/audit")
    public Response courseAudit(@RequestBody CourseVideo course) {
        Integer id = course.getId();
        Integer auditStatus = course.getAuditStatus();
        String auditNotice = course.getAuditNotice();
        Assert.notNull(id, "课程视频编号不能为空");
        CourseVideo byId = courseVideoService.getById(id);
        Assert.isTrue(StatusEnum.WAIT.equalsStatus(byId.getAuditStatus()), "该课程不处于待审核状态");
        Assert.isTrue(StatusEnum.containsStatus(auditStatus), "审核决定不正确");
        Assert.isFalse(StringUtils.isBlank(auditNotice) && StatusEnum.FAIL.equalsStatus(auditStatus), "审核不通过原因不能为空");
        CourseVideo updatedBy = new CourseVideo().setId(id).setAuditStatus(auditStatus).setAuditNotice(auditNotice)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        courseVideoService.updateById(updatedBy);
        return Response.ok("审核操作成功");
    }


    /**
     * 删除视频
     * @param courseVo 视频实体
     * @return response
     */
    @PostMapping("/delete")
    public Response courseDelete(@RequestBody CourseVideoVo courseVo) {
        Integer id = courseVo.getId();
        Assert.notNull(id, "视频编号不能为空");
        CourseVideo byId = courseVideoService.getById(id);
        Assert.notNull(byId, "课程视频不存在");
        LambdaQueryWrapper<UserCourseVideo> query = Wrappers.lambdaQuery();
        query.eq(UserCourseVideo::getCourseId, byId.getCourseId())
                .eq(UserCourseVideo::getVideoId, id);
        int count = userCourseVideoService.count(query);
        Assert.equals(0, count, "该课程视频已有学生在学习不允许被删除");
        courseVideoService.removeById(id);
        return ResponseHelper.deleteSuccess();
    }


    /**
     * 更新视频
     * @param courseVo 课程视频实体
     * @return response
     */
    @PostMapping("/update")
    public Response courseUpdate(@RequestBody @Validated CourseVideoVo courseVo) {
        Integer courseId = courseVo.getCourseId();
        Assert.notNull(courseId, "课程编号不能为空");
        Integer id = courseVo.getId();
        Assert.notNull(id, "课程视频编号不能为空");
        CourseVideo byId = courseVideoService.getById(id);
        Assert.notNull(byId, "课程视频不存在");
        CourseVideo map = mapperFacade.map(courseVo, CourseVideo.class);
        if (StringUtils.isBlank(courseVo.getThumbUrl())) {
            map.setThumbUrl(CommonConstant.DEFAULT_THUMB_URL);
        }
        // 更新视频信息
        map.setAuditNotice(StringUtils.EMPTY)
                .setAuditStatus(StatusEnum.WAIT.getStatus())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId())
                .setDisplayOrder(1);
        courseVideoService.updateById(map);
        return Response.ok("视频更新成功");
    }
}
