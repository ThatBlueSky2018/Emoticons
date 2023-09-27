package org.pancakeapple.service;

import org.pancakeapple.dto.user.LoginDTO;
import org.pancakeapple.dto.user.RegisterDTO;
import org.pancakeapple.vo.user.LoginVO;
import org.pancakeapple.vo.user.RegisterVO;

public interface UserService {
    LoginVO login(LoginDTO loginDTO);

    RegisterVO register (RegisterDTO registerDTO);
}
