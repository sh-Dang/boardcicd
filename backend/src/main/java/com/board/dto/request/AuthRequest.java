package com.board.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class AuthRequest {

    @Getter
    public static class SignUp {
        @NotBlank(message = "아이디를 입력해주세요")
        @Size(min = 4, max = 20, message = "아이디는 4~20자 이내로 입력해주세요")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 8, message = "비밀번호는 8자 이상 입력해주세요")
        private String password;

        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        private String email;

        @NotBlank(message = "닉네임을 입력해주세요")
        @Size(min = 2, max = 10, message = "닉네임은 2~10자 이내로 입력해주세요")
        private String nickname;
    }

    @Getter
    public static class Login {
        @NotBlank(message = "아이디를 입력해주세요")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;
    }
}
