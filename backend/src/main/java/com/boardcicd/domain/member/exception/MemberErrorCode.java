package com.boardcicd.domain.member.exception;

import com.boardcicd.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 유저와 관련된 예외들을 모아둔 객체
 * 회원 존재하지 않음, 이미 존재하는 이메일, 이미 존재하는 닉네임
 */
@Getter
public enum MemberErrorCode implements ErrorCode {

    WRONG_EMAIL_OR_PASSWORD(HttpStatus.NOT_FOUND, "이메일 혹은 비밀번호를 확인해주세요"),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 가입된 이메일입니다."), //409
    NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "누군가 사용중인 닉네임 입니다."); //409

    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
