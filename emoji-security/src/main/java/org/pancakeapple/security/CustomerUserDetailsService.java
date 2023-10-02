package org.pancakeapple.security;

import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.entity.user.Role;
import org.pancakeapple.entity.user.User;
import org.pancakeapple.entity.user.UserRole;
import org.pancakeapple.mapper.user.RoleMapper;
import org.pancakeapple.mapper.user.UserMapper;
import org.pancakeapple.mapper.user.UserRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailService实现类
 * Author SKY 2023/9/19
 */
@Component
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    //此方法中通过用户名加载用户信息时,会确定用户的角色,在JwtAuthenticationFilter中被调用,通过角色确定用户的权限
    //将角色转化为对应权限的代码在User实体类中
    @Override
    public UserDetails loadUserByUsername(String username){
        log.info("Spring Security根据用户名加载用户...");
        User user = userMapper.findUserByUsername(username);
        if(user != null) {
            //确定用户的角色
            List<UserRole> userRoles = userRoleMapper.findRolesByUser(user.getId());
            List<Role> roleList = new ArrayList<>();
            userRoles.forEach(userRole -> {
                Role role = roleMapper.findRoleById(userRole.getRoleId());
                roleList.add(role);
            });
            user.setRoles(roleList);
        }

        return user;
    }
}
