package com.boardcicd.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
  회원가입에 요청을 보낼때 사용할 DTO객체
 */
public class SignUpRequestDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
}
