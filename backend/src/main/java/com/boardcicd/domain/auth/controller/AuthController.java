package com.boardcicd.domain.auth.controller;

import com.boardcicd.domain.auth.service.AuthService;
import com.boardcicd.domain.member.dto.LoginRequestDto;
import com.boardcicd.domain.member.dto.LoginResponseDto;
import com.boardcicd.domain.member.dto.SignupRequestDto;
import com.boardcicd.domain.member.dto.SignupResponseDto;
import com.boardcicd.global.dto.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ApiResponse.success(authService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ApiResponse.success(authService.login(loginRequestDto));
    }
}
