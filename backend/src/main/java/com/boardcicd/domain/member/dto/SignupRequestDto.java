package com.boardcicd.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
  회원가입에 요청을 보낼때 사용할 DTO객체
 */
public class SignupRequestDto {
    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 4, max = 20, message = "아이디는 4~20자 이내로 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 이내로 입력해주세요")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Size(min = 2, max = 10, message = "닉네임은 2~10자 이내로 입력해주세요")
    private String nickname;
}
