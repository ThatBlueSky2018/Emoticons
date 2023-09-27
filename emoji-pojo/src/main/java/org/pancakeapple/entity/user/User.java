package org.pancakeapple.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
    private Long id;
    private String username;
    private String password;
    private String profilePhoto;
    private LocalDateTime registerTime;

    //该字段数据库中没有
    private List<Role> roles;

    /**
     * @return Roles the User has
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回用户角色认证信息的逻辑
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
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
        return true;
    }
}
