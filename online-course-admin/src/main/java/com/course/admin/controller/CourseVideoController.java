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
 * ??????
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
     * ????????????????????????
     * @param page ??????
     * @param pageSize ?????????
     * @param auditStatus ????????????
     * @param keyword ?????????
     * @param courseId ??????ID
     * @return response
     */
    @GetMapping("/list")
    public Response courseList(Integer page, Integer pageSize, Integer auditStatus, String keyword,
                               @NotNull(message = "????????????????????????") Integer courseId) {

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
            // ????????????M
            map.setFileSizeTitle(FileSizeUtil.formatFileSize(item.getFileSize()));
            return map;
        });
        return Response.ok(paging);
    }

    /**
     * ????????????????????????
     *
     * @param page ??????
     * @param pagesSize ?????????
     * @param keyword ?????????
     * @param courseId ??????ID
     * @return response
     */
    @GetMapping("/student-list")
    public Response courseUserList(Integer page, Integer pagesSize, String keyword, @NotNull(message = "??????????????????????????????") Integer courseId) {
        CourseVideo byId = courseVideoService.getById(courseId);
        Assert.notNull(byId, "?????????????????????");
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
     * ??????????????????
     * @param courseVo ????????????
     * @return response
     */
    @PostMapping("/create")
    public Response courseCreate(@RequestBody @Validated CourseVideoVo courseVo) {
        Integer courseId = courseVo.getCourseId();
        Assert.notNull(courseId, "????????????????????????");

        CourseVideo map = mapperFacade.map(courseVo, CourseVideo.class);
        if (StringUtils.isBlank(courseVo.getThumbUrl())) {
            map.setThumbUrl(CommonConstant.DEFAULT_THUMB_URL);
        }
        // ????????????
        map.setAuditNotice(StringUtils.EMPTY)
                .setAuditStatus(StatusEnum.WAIT.getStatus())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId())
                .setDisplayOrder(1);
        courseVideoService.save(map);
        return Response.ok("????????????????????????");
    }

    /**
     * ????????????
     * @param course ????????????
     * @return response
     */
    @PostMapping("/audit")
    public Response courseAudit(@RequestBody CourseVideo course) {
        Integer id = course.getId();
        Integer auditStatus = course.getAuditStatus();
        String auditNotice = course.getAuditNotice();
        Assert.notNull(id, "??????????????????????????????");
        CourseVideo byId = courseVideoService.getById(id);
        Assert.isTrue(StatusEnum.WAIT.equalsStatus(byId.getAuditStatus()), "?????????????????????????????????");
        Assert.isTrue(StatusEnum.containsStatus(auditStatus), "?????????????????????");
        Assert.isFalse(StringUtils.isBlank(auditNotice) && StatusEnum.FAIL.equalsStatus(auditStatus), "?????????????????????????????????");
        CourseVideo updatedBy = new CourseVideo().setId(id).setAuditStatus(auditStatus).setAuditNotice(auditNotice)
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        courseVideoService.updateById(updatedBy);
        return Response.ok("??????????????????");
    }


    /**
     * ????????????
     * @param courseVo ????????????
     * @return response
     */
    @PostMapping("/delete")
    public Response courseDelete(@RequestBody CourseVideoVo courseVo) {
        Integer id = courseVo.getId();
        Assert.notNull(id, "????????????????????????");
        CourseVideo byId = courseVideoService.getById(id);
        Assert.notNull(byId, "?????????????????????");
        LambdaQueryWrapper<UserCourseVideo> query = Wrappers.lambdaQuery();
        query.eq(UserCourseVideo::getCourseId, byId.getCourseId())
                .eq(UserCourseVideo::getVideoId, id);
        int count = userCourseVideoService.count(query);
        Assert.equals(0, count, "??????????????????????????????????????????????????????");
        courseVideoService.removeById(id);
        return ResponseHelper.deleteSuccess();
    }


    /**
     * ????????????
     * @param courseVo ??????????????????
     * @return response
     */
    @PostMapping("/update")
    public Response courseUpdate(@RequestBody @Validated CourseVideoVo courseVo) {
        Integer courseId = courseVo.getCourseId();
        Assert.notNull(courseId, "????????????????????????");
        Integer id = courseVo.getId();
        Assert.notNull(id, "??????????????????????????????");
        CourseVideo byId = courseVideoService.getById(id);
        Assert.notNull(byId, "?????????????????????");
        CourseVideo map = mapperFacade.map(courseVo, CourseVideo.class);
        if (StringUtils.isBlank(courseVo.getThumbUrl())) {
            map.setThumbUrl(CommonConstant.DEFAULT_THUMB_URL);
        }
        // ??????????????????
        map.setAuditNotice(StringUtils.EMPTY)
                .setAuditStatus(StatusEnum.WAIT.getStatus())
                .setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId())
                .setDisplayOrder(1);
        courseVideoService.updateById(map);
        return Response.ok("??????????????????");
    }
}
