package org.pancakeapple.service.impl;

import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.constant.RBACConstant;
import org.pancakeapple.dto.user.LoginDTO;
import org.pancakeapple.dto.user.RegisterDTO;
import org.pancakeapple.mapper.RoleMapper;
import org.pancakeapple.mapper.UserMapper;
import org.pancakeapple.mapper.UserRoleMapper;
import org.pancakeapple.entity.user.Role;
import org.pancakeapple.entity.user.User;
import org.pancakeapple.entity.user.UserRole;
import org.pancakeapple.service.UserService;
import org.pancakeapple.utils.JwtUtils;
import org.pancakeapple.vo.user.LoginVO;
import org.pancakeapple.vo.user.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        //这一步可能会抛出异常，交给Controller层处理
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User loginUser = userMapper.findUserByUsername(authentication.getName());

        //确定该用户的角色
        List<String> rolesNames = new ArrayList<>();
        List<UserRole> userRoles = userRoleMapper.findRolesByUser(loginUser.getId());
        userRoles.forEach(userRole -> {
            Role role = roleMapper.findRoleById(userRole.getRoleId());
            rolesNames.add(role.getRoleName());
        });

        //生成JWT令牌
        Map<String, Object> claims=new HashMap<>();
        claims.put(RBACConstant.USER_ID,loginUser.getId());
        claims.put(RBACConstant.USER_NAME, loginUser.getUsername());
        claims.put(RBACConstant.USER_ROLES,rolesNames);
        String jwt=JwtUtils.generateJwt(claims);

        return LoginVO.builder()
                .id(loginUser.getId())
                .username(loginUser.getUsername())
                .token(jwt)
                .build();
    }


    @Override
    public RegisterVO register(RegisterDTO registerDTO) {
        //用户名已经存在的情况
        if(userMapper.findUserByUsername(registerDTO.getUsername())!=null) {
            return RegisterVO.builder().msg(MessageConstant.ACCOUNT_EXIST).build();
        }
        //默认角色是USER
        Role role = roleMapper.findRoleByRoleName(RBACConstant.DEFAULT_ROLE);
        List<Role> roleList=new ArrayList<>();
        roleList.add(role);

        User newUser = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .roles(roleList)
                .build();
        newUser.setRoles(roleList);
        userMapper.add(newUser);

        //查询回显,维护多对多关系中间表
        userRoleMapper.insert(newUser.getId(), role.getId());
        return RegisterVO.builder().msg(MessageConstant.REGISTER_SUCCESS).build();
    }

}
