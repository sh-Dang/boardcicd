package com.boardcicd.domain.post.exception;

/**
 * 게시글 관련 예외들을 처리해주는 객체
 * */
public class PostException extends RuntimeException {
    public PostException(String message) {
        super(message);
    }
}
