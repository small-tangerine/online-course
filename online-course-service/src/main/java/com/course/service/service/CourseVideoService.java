package com.course.service.service;

import com.course.api.entity.CourseVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.commons.model.Paging;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频关联 服务类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
public interface CourseVideoService extends IService<CourseVideo> {

    List<CourseVideo> listByCourseId(Integer id);

    void pagingByParamsMap(Paging<CourseVideo> paging, Map<String, Object> paramsMap);
}
