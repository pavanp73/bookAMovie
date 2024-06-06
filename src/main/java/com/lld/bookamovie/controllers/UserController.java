package com.lld.bookamovie.controllers;

import com.lld.bookamovie.dtos.LoginRequestDto;
import com.lld.bookamovie.dtos.LoginResponseDto;
import com.lld.bookamovie.dtos.ResponseStatus;
import com.lld.bookamovie.dtos.SignupRequestDto;
import com.lld.bookamovie.dtos.SignupResponseDto;
import com.lld.bookamovie.models.User;
import com.lld.bookamovie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupResponseDto signUp(SignupRequestDto requestDto) {
        // Todo - handle exception
        User user = userService.singUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        SignupResponseDto responseDto = new SignupResponseDto();
        responseDto.setUserId(user.getId());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        LoginResponseDto responseDto = new LoginResponseDto();

        User user = userService.login(requestDto.getEmail(), requestDto.getPassword());
        if (user == null) {
            responseDto.setResponseStatus(ResponseStatus.FAIL);
        } else {
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }

        return responseDto;
    }
}
