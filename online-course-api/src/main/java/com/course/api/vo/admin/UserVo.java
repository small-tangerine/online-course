package com.course.api.vo.admin;

import com.course.commons.annotations.AccountInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

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
     * 密码
     */
    @Pattern(regexp = "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*",
            message = "新密码必须长度大于或等于8个字符，必须是由字母大小写、数字、其他特殊符号的组合",
            groups = AccountInfo.class)
    private String password;
    private String checkPassword;
    private String oldPassword;

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

    private LocalDateTime createdAt;

    private Integer roleId;
    private String roleTitle;

}
