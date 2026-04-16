package com.boardcicd.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private String accessToken; // accessToken만 반환하여 메모리이동 최소화
}
