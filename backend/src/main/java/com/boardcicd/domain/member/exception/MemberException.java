package com.boardcicd.domain.member.exception;

import com.boardcicd.global.exception.BusinessException;
import com.boardcicd.global.exception.ErrorCode;

public class MemberException extends BusinessException {

    public MemberException(MemberErrorCode errorCode){
        super(errorCode);
    }
}
