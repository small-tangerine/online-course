package com.course.server.config.security.handler;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.course.commons.enums.ResultCodeEnum;
import com.course.commons.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 *
 * @author macro
 * @date 2018/5/14
 */
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(ContentType.JSON.getValue());
        response.setStatus(HttpStatus.HTTP_OK);
        log.error(String.format("权限提示: %s", authException.getMessage()));
        response.getWriter().println(JSONUtil.parse(Response.fail(ResultCodeEnum.UNAUTHORIZED.getCode(),
                ResultCodeEnum.UNAUTHORIZED.getDesc())));
        response.getWriter().flush();
    }
}
