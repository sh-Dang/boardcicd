package com.boardcicd.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponseDto {
    private Long id;
    private String username;
    private String email;
    private String nickname;
}
