package com.boardcicd.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
  로그인 요청을 보낼때 사용할 DTO객체
 */
public class LoginRequestDto { // 로그인 요청 받을 dto 유저네임과 비밀번호만 포함
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
