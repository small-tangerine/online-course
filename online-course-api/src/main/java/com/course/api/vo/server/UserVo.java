package com.course.api.vo.server;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class UserVo {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * ID
     */
    private String uid;

    /**
     * 账号
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 职位
     */
    private String job;

    /**
     * 学习时长/h
     */
    private Long learnHour;

    /**
     * 性别 unknown male female
     */
    private String sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 个性签名
     */
    private String signature;

    private String token;
}
