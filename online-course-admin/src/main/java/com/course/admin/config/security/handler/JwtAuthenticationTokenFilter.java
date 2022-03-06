package com.course.admin.config.security.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.JwtUtil;
import com.course.admin.config.security.model.LoginUser;
import com.course.api.entity.UserToken;
import com.course.commons.enums.ResultCodeEnum;
import com.course.commons.utils.Assert;
import com.course.service.service.UserTokenService;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.AccessException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * JWT登录授权过滤器
 *
 * @author macro
 * @date 2018/4/26
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserTokenService userTokenService;

    public JwtAuthenticationTokenFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil, UserTokenService userTokenService) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userTokenService = userTokenService;
    }

    private static final String AUTH_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // 进入校验token
        String authToken = request.getHeader(AUTH_HEADER);
        if (StringUtils.isBlank(authToken)){
            chain.doFilter(request,response);
            return;
        }
        try {
            //通过token获取用信息
            String username = jwtUtil.getUserNameFromToken(authToken);
            Assert.notBlank(username, "通过token【{}】找不到相应用户", authToken);
            LoginUser userDetails = (LoginUser) userDetailsService.loadUserByUsername(username);
            //验证token用户信息是否与数据库一致
            if (jwtUtil.validateToken(authToken, userDetails)) {
                //判断是否存在该用户
                if (!onlineUserUpdateStatus(username, authToken)) {
                    //不存在则直接返回未登录或token过期提示
                    throw new SignatureException(ResultCodeEnum.UNAUTHORIZED.getDesc());
                }
                response.setHeader(AUTH_HEADER, authToken);
                response.setHeader("Access-Control-Expose-Headers", AUTH_HEADER);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("authenticated :{}", e.getMessage());
            throw new AccessException("用户权限异常");
        }
        chain.doFilter(request, response);
    }

    private boolean onlineUserUpdateStatus(String username, String token) {
        LambdaQueryWrapper<UserToken> query = Wrappers.lambdaQuery();
        query.eq(UserToken::getToken, token)
                .eq(UserToken::getUsername, username)
                .gt(UserToken::getExpiredAt, LocalDateTime.now())
                .last("limit 1");
        UserToken userToken = userTokenService.getOne(query);
        if (Objects.nonNull(userToken)) {
            LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(30L);
            UserToken updateToken = new UserToken().setId(userToken.getId())
                    .setExpiredAt(expiredAt);
            userTokenService.updateById(updateToken);
        }
        return Objects.nonNull(userToken);
    }

}
