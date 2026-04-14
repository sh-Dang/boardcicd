package com.boardcicd.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
/*
  로그인 요청을 보낼때 사용할 DTO객체
 */
public class LoginRequestDto { // 로그인 요청 받을 dto 유저네임과 비밀번호만 포함
    private String username;
    private String password;
}
