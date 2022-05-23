package com.course.api.vo;

import com.course.commons.annotations.Forget;
import com.course.commons.annotations.Login;
import com.course.commons.annotations.Register;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户
 * </p>
 *
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
public class LoginVo {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空", groups = {Login.class, Register.class,Forget.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Login.class, Register.class,Forget.class})
    @Pattern(regexp = "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*",
            message = "密码必须长度大于或等于8个字符，必须是由字母大小写、数字、其他特殊符号的组合",
            groups = {Register.class,Forget.class})
    private String password;

    private String checkPassword;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    @NotBlank(message = "验证吗不能为空", groups = {Forget.class})
    private String verifyCode;
}
