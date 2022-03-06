package com.course.admin.config.security.model;


import com.course.api.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author macro
 * @date 2018/4/26
 */
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 8450640813418346484L;

    private final User user;
    private final Collection<String> permissionList;

    private final Integer role;

    public LoginUser(User user, Collection<String> permissionList,Integer role) {
        this.user = user;
        this.permissionList = permissionList;
        this.role =role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return permissionList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Integer getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public Integer getUserRole() {
        return role;
    }
}
