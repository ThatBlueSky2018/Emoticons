package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.dto.user.LoginDTO;
import org.pancakeapple.dto.user.RegisterDTO;
import org.pancakeapple.dto.user.UserInfoDTO;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.UserService;
import org.pancakeapple.vo.user.LoginVO;
import org.pancakeapple.vo.user.RegisterVO;
import org.pancakeapple.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Tag(name="用户相关接口")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 注册提示信息
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<RegisterVO> register(@RequestBody RegisterDTO registerDTO) {
        log.info("用户注册：{}",registerDTO);
        RegisterVO registerVO = userService.register(registerDTO);
        if(registerVO.getMsg().equals(PromptConstant.ACCOUNT_EXIST)) {
            return Result.error(PromptConstant.ACCOUNT_EXIST);
        }
        return Result.success(registerVO);
    }

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return LoginVO 登录后返回的数据
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录：{}",loginDTO);
        LoginVO loginVO;
        try {
            loginVO = userService.login(loginDTO);
        } catch (AuthenticationException e) {
            return Result.error(PromptConstant.ACCOUNT_ERROR);
        }
        return Result.success(loginVO);
    }

    /**
     * 根据id获取用户信息
     * @param id 主键id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id获取用户信息")
    public Result<UserInfoVO> getById(@PathVariable Long id) {
        log.info("根据id获取用户信息：{}",id);
        UserInfoVO userInfoVO=userService.getById(id);
        return Result.success(userInfoVO);
    }

    /**
     * 修改用户信息
     * @param userInfoDTO 用户信息封装
     * @return 提示信息
     */
    @PutMapping
    @Operation(summary = "用户修改信息")
    public Result<String> update(@RequestBody UserInfoDTO userInfoDTO) {
        log.info("修改用户信息，{}",userInfoDTO);
        userService.update(userInfoDTO);
        return Result.success(PromptConstant.UPDATE_SUCCESS);
    }

}
