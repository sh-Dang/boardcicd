package com.boardcicd.domain.auth.exception;

import com.boardcicd.global.exception.BusinessException;

/**
 * 인증관련 예외들을 처리해주는 객체
 */
public class AuthException extends BusinessException {

    public AuthException(AuthErrorCode errorCode){
        super(errorCode);
    }
}
