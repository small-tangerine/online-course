package com.course.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程视频
 *
 * @author panguangming
 * @since 2022-03-05
 */
@RequestMapping("/course-video")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class VideoController {
}
