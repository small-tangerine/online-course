package com.course.config.security;

import cn.hutool.core.text.CharSequenceUtil;
import com.course.api.entity.User;
import com.course.commons.utils.TimeUtils;
import com.course.config.security.model.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMAC SHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 * @author macro
 * @date 2018/4/26
 */
@Slf4j
public class JwtUtil {

    private static final String SECRET="online-course";

    /**
     * 根据负责生成JWT的token
     *
     * @param claims 加密信息
     * @return java.lang.String
     * @since 2020/11/22 01:13
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     *
     * @param token 用户token
     * @return io.jsonwebtoken.Claims
     * @since 2020/11/22 01:13
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return Objects.nonNull(claims) ? claims.getSubject() : CharSequenceUtil.EMPTY;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, LoginUser userDetails) {
        Claims claims = getClaimsFromToken(token);
        Long time = Objects.nonNull(claims) ? (Long) claims.get("iat") : 0;
        String username = getUserNameFromToken(token);
        return userDetails != null && username.equals(userDetails.getUsername()) && time.compareTo((new Date()).getTime()) < 0;
    }

    /**
     * 根据用户信息生成token
     *
     * @param userDetails 用户信息
     * @return java.lang.String
     * @since 2020/11/22 01:11
     */
    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put("sub", userDetails.getUsername());
        claims.put("iat", TimeUtils.getTimestampOfDateTime(userDetails.getCreatedAt()));
        claims.put("uuid", userDetails.getId());
        claims.put("name", userDetails.getNickname());
        claims.put("jti", UUID.randomUUID().toString());
        return generateToken(claims);
    }

    /**
     * 通过token获取当前用户id
     *
     * @param authToken token
     * @return user_id
     */
    public Integer getUserLoginId(String authToken) {
        Claims claims = getClaimsFromToken(authToken);
        return Objects.nonNull(claims) ? Integer.valueOf(claims.get("uuid").toString()) : null;
    }
}
