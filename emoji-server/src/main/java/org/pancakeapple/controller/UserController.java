package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<RegisterVO> register(@RequestBody RegisterDTO registerDTO) {
        RegisterVO registerVO = userService.register(registerDTO);
        if(registerVO.getMsg().equals(MessageConstant.ACCOUNT_EXIST)) {
            return Result.error(MessageConstant.ACCOUNT_EXIST);
        }
        return Result.success(registerVO);
    }

    /**
     * 用户登录
     * @return LoginVO
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
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
