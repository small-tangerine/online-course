package com.course.admin.config.security;

import com.course.admin.config.security.handler.DynamicAccessDecisionManager;
import com.course.admin.config.security.handler.DynamicSecurityFilter;
import com.course.admin.config.security.handler.DynamicSecurityMetadataSource;
import com.course.admin.config.security.handler.DynamicSecurityService;
import com.course.admin.config.security.handler.JwtAuthenticationTokenFilter;
import com.course.admin.config.security.handler.RestAuthenticationEntryPoint;
import com.course.admin.config.security.handler.RestfulAccessDeniedHandler;
import com.course.admin.config.security.service.SecurityService;
import com.course.api.vo.admin.PermissionVo;
import com.course.component.cache.PermissionCache;
import com.course.service.service.UserTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * ???SpringSecurity??????????????????????????????????????????????????????????????????????????????
 *
 * @author macro
 * @date 2019/11/5
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private PermissionCache permissionCache;
    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();

        // ??????????????????????????????
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // ????????????????????????????????????session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // ??????????????????????????????
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                .and()
                .logout()
                // ????????????????????????JWT?????????
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //?????????????????????????????????????????????????????????
        if (dynamicSecurityService != null) {
            registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        //??????????????????????????????????????????: ???????????????????????????
        webSecurity.ignoring().antMatchers("/account/login",
                "/account/reset-password");
        //?????????????????????OPTIONS??????
        webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS);

    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //????????????????????????
        return securityService::loadUserByUsername;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(userDetailsService(), jwtUtil(), userTokenService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {

        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>(16);
            //??????????????????????????????
            List<PermissionVo> permissionVoList = permissionCache.getPermissionVoList();
            List<PermissionVo> collect = permissionVoList.stream().filter(item -> StringUtils.isNotBlank(item.getPermissionTag())).collect(Collectors.toList());
            for (PermissionVo permission : collect) {
                map.put(permission.getPath(), new org.springframework.security.access.SecurityConfig(permission.getPermissionTag()));
            }
            return map;
        };
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter(dynamicSecurityMetadataSource(),dynamicAccessDecisionManager());
    }

    /**
     * ????????????????????????
     *
     * @return ?????????????????????
     */
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }
}
