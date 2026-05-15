package com.boardcicd.global.exception;

import com.boardcicd.global.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * BusinessException 처리
     * <p>
     * 서비스 로직에서 발생하는 사용자 정의 예외를 처리한다.
     * <p>
     * ErrorCode에 정의된 상태코드와 메시지를 기반으로 응답 생성
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException e
    ) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode.getMessage()));
    }
    /**
     * DTO Validation 실패 예외 처리
     * <p>
     * &#064;Valid  검증 실패 시 발생하는 MethodArgumentNotValidException 처리
     * @since 2026/05/15
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException e
    ) {

        String message = Objects.requireNonNull(e.getBindingResult()
                        .getFieldError())
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(message));
    }
}
