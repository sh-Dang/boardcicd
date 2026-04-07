package com.boardcicd.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    String username;
    String password;
}
