package com.course.admin.config.security.handler;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.course.commons.enums.ResultCodeEnum;
import com.course.commons.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：没有权限访问时
 *
 * @author macro
 * @date 2018/4/26
 */
@Slf4j
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(ContentType.JSON.getValue());
        response.setStatus(HttpStatus.HTTP_OK);
        log.error(String.format("权限提示: %s", e.getMessage()));
        response.getWriter().println(JSONUtil.parse(Response.fail(ResultCodeEnum.AUTH_NOT_ENOUGH.getDesc())));
        response.getWriter().flush();
    }
}
