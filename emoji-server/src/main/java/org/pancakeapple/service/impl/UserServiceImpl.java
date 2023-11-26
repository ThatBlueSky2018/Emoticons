package org.pancakeapple.service.impl;

import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.constant.RBACConstant;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.dto.user.LoginDTO;
import org.pancakeapple.dto.user.RegisterDTO;
import org.pancakeapple.dto.user.UserInfoDTO;
import org.pancakeapple.exception.UserNameExistException;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.pancakeapple.mapper.interaction.FavoriteMapper;
import org.pancakeapple.mapper.user.RoleMapper;
import org.pancakeapple.mapper.user.UserMapper;
import org.pancakeapple.mapper.user.UserRoleMapper;
import org.pancakeapple.entity.user.Role;
import org.pancakeapple.entity.user.User;
import org.pancakeapple.entity.user.UserRole;
import org.pancakeapple.service.UserService;
import org.pancakeapple.utils.JwtUtils;
import org.pancakeapple.vo.user.LoginVO;
import org.pancakeapple.vo.user.RegisterVO;
import org.pancakeapple.vo.user.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private EmojiMapper emojiMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 登录返回的数据
     */
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

        //更新上次登录时间
        loginUser.setLastLogin(LocalDateTime.now());
        userMapper.update(loginUser);

        //确定该用户的角色
        List<String> rolesNames = new ArrayList<>();
        List<UserRole> userRoles = userRoleMapper.findRolesByUserId(loginUser.getId());
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


    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 注册之后返回的数据
     */
    @Override
    @Transactional
    public RegisterVO register(RegisterDTO registerDTO) {
        //用户名已经存在的情况
        if(userMapper.findUserByUsername(registerDTO.getUsername())!=null) {
            throw new UserNameExistException(PromptConstant.ACCOUNT_EXIST);
        }
        //默认角色是USER
        Role role = roleMapper.findRoleByRoleName(RBACConstant.DEFAULT_ROLE);
        List<Role> roleList=new ArrayList<>();
        roleList.add(role);

        //插入数据
        User newUser = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .createTime(LocalDateTime.now())
                .roles(roleList)
                .build();
        newUser.setRoles(roleList);
        newUser.setCreateTime(LocalDateTime.now());
        userMapper.addUser(newUser);

        //查询回显,维护多对多关系中间表
        UserRole userRole = new UserRole();
        userRole.setUserId(newUser.getId());
        userRole.setRoleId(role.getId());
        userRoleMapper.insert(userRole);
        return RegisterVO.builder().msg(PromptConstant.REGISTER_SUCCESS).build();
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 主键id
     * @return 用户信息
     */
    @Override
    public UserInfoVO getById(Long id) {
        UserInfoVO userInfo = userMapper.getById(id);
        Integer uploadCount = emojiMapper.getUploadCountByUserId(id);
        userInfo.setUploadCount(uploadCount);
        Integer favoriteCount = favoriteMapper.getFavoriteCountByUserId(id);
        userInfo.setFavoriteCount(favoriteCount);
        return userInfo;
    }

    /**
     * 修改用户信息
     * @param userInfoDTO 用户信息封装
     */
    @Override
    public void update(UserInfoDTO userInfoDTO) {
        //查看是否修改了用户名
        UserInfoVO userInfo = userMapper.getById(BaseContext.getCurrentId());
        if(!Objects.equals(userInfo.getUsername(), userInfoDTO.getUsername())) {
            if(userMapper.findUserByUsername(userInfoDTO.getUsername())!=null) {
                throw new UserNameExistException(PromptConstant.ACCOUNT_EXIST);
            }
        }

        //拷贝用户信息，进行修改
        User user=new User();
        BeanUtils.copyProperties(userInfoDTO, user);
        user.setId(BaseContext.getCurrentId());
        userMapper.update(user);
    }

}
