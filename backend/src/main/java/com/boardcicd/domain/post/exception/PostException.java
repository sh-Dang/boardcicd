package com.boardcicd.domain.post.exception;

import com.boardcicd.global.exception.BusinessException;

/**
 * 게시글 관련 예외들을 처리해주는 객체
 * */
public class PostException extends BusinessException {

    public PostException(PostErrorCode errorCode) {
        super(errorCode);
    }

}
