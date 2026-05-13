package com.boardcicd.domain.auth.exception;

import com.boardcicd.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 인증과 관련된 예외들을 모아서 관리하는 객체
 */
@Getter
public enum AuthErrorCode implements ErrorCode {
    NOT_AVAILABLE_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 토큰입니다."), // 403
    TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "토큰을 재발급 받으시기 바랍니다."); // 403

    private final HttpStatus status;
    private final String message;

    AuthErrorCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
