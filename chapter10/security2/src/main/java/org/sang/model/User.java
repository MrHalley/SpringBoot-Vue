package org.sang.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类需要实现UserDetails接口，并实现该接口的7个方法
 *  getAuthorities();获取当前用户所具有的角色信息
 *  getPassword();获取当前用户对象的密码
 *  getUsername();获取当前用户对象的用户名
 *  isAccountNotExpired();当前账户是否未过期
 *  isAccountNonLocked();当前用户是否未锁定
 *  isCredentialsNonExpired();当前账户密码是否未过期
 *  isEnabled();当前账户是否可用
 */
@Data
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
