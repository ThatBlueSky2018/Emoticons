package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.user.LoginDTO;
import org.pancakeapple.dto.user.RegisterDTO;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.UserService;
import org.pancakeapple.vo.user.LoginVO;
import org.pancakeapple.vo.user.RegisterVO;
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
        if(registerVO.getMsg().equals(MessageConstant.ACCOUNT_EXIST)) {
            return Result.error(MessageConstant.ACCOUNT_EXIST);
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
            return Result.error(MessageConstant.ACCOUNT_ERROR);
        }
        return Result.success(loginVO);
    }

    @GetMapping("/user")
    public Result<String> user() {
        System.out.println("UserController user...");
        return Result.success("OK");
    }

    @GetMapping("/admin")
    public Result<String> admin() {
        System.out.println("UserController admin...");
        return Result.success("OK");
    }

}
