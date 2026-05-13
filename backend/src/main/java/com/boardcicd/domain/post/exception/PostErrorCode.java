package com.boardcicd.domain.post.exception;

import com.boardcicd.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum PostErrorCode implements ErrorCode {

    // 404
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글 입니다."),
    // 403
    NO_PERMISSION(HttpStatus.FORBIDDEN, "게시글 수정 권한이 없습니다.");

    private final HttpStatus status;
    private final String message;

    // 생성자 직접 생성
    PostErrorCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}