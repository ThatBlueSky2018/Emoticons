package org.pancakeapple.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.enumeration.Gender;
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
    public static Integer BLOCKED=0; //账号封禁
    public static Integer ACTIVE=1; //账号激活

    public static Integer NOT_OFFICIAL=0; //不是官方账号
    public static Integer IS_OFFICIAL=1;  //是官方账号
    private Long id;
    private String email;
    private String username;
    private String password;
    private Gender gender;
    private String profilePhoto;
    private String signature;
    private LocalDateTime lastLogin;

    private Integer status; //账号状态：0表示封禁，1表示激活
    private Integer isOfficial; //是否是官方账号

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updateUser;

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
