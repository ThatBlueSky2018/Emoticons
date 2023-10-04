package org.pancakeapple.service;

import org.pancakeapple.dto.user.LoginDTO;
import org.pancakeapple.dto.user.RegisterDTO;
import org.pancakeapple.vo.user.LoginVO;
import org.pancakeapple.vo.user.RegisterVO;
import org.pancakeapple.vo.user.UserInfoVO;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 登录返回的数据
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 注册之后返回的数据
     */
    RegisterVO register (RegisterDTO registerDTO);

    /**
     * 根据id查询用户信息
     * @param id 主键id
     * @return 用户信息
     */
    UserInfoVO getById(Long id);
}
