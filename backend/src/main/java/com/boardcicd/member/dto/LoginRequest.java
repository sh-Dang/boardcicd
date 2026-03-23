package com.boardcicd.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest { // 로그인 요청 받을 dto 유저네임과 비밀번호만 포함
    private String username;
    private String password;
}
