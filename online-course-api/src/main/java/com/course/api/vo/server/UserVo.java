package com.course.api.vo.server;

import com.course.api.entity.Teachers;
import com.course.commons.annotations.AccountInfo;
import com.course.commons.annotations.BaseInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Collection;

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
            message = "密码必须长度大于或等于8个字符，必须是由字母大小写、数字、其他特殊符号的组合",
            groups = AccountInfo.class)
    private String password;

    private String checkPassword;

    /**
     * 手机号
     */
    @NotBlank(message = "请输入手机号", groups = AccountInfo.class)
    private String mobile;

    /**
     * 邮箱
     */
    @NotBlank(message = "请输入邮箱", groups = AccountInfo.class)
    @Email(message = "请输入正确的邮箱", groups = AccountInfo.class)
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
    @Length(max = 250, message = "职位长度不能超过16字", groups = BaseInfo.class)
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
    @Length(max = 250, message = "城市名称长度不能超过64字", groups = BaseInfo.class)
    private String city;

    /**
     * 个性签名
     */
    @Length(max = 250, message = "个性签名长度不能超过250字", groups = BaseInfo.class)
    private String signature;

    private String token;

    private LocalDateTime createdAt;

    private Integer roleId;
    private String roleTitle;

    private Teachers teacherInfo;

    private Collection<String> roles;
}
