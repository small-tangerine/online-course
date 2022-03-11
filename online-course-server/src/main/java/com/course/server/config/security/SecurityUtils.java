package com.course.server.config.security;


import com.course.server.config.security.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;


/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
@Slf4j
public class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return Objects.requireNonNull(getLoginUser()).getUsername();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        Object principal = null;
        try {
            principal = getAuthentication().getPrincipal();
        } catch (Exception e) {
            log.error("获取用户登录信息失败：{}", e.getMessage());
        }
        return Objects.nonNull(principal) ? (principal instanceof LoginUser ? (LoginUser) principal : null) : null;
    }

    /**
     * 获取用户账户
     */
    public static Integer getUserId() {
        LoginUser loginUser = getLoginUser();
        return Objects.nonNull(loginUser) ? loginUser.getId() : null;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
