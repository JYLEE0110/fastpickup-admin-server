package org.fktm.fastpickup.member.exception.enumcode;

import org.fktm.fastpickup.exception.enumcode.ExceptionCode;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {
    
    // ID 중복 예외 enum 
    DUPLICATED_MEMBER_ID(HttpStatus.BAD_REQUEST, "이미 사용중인 ID입니다."),

    // 비밀번호 검증 실패
    MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST, "입력한 비밀번호와 일치하지않습니다."),

    // 회원탈퇴 시 존재하지않는 회원일 때
    NOT_EXCIST_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
    
    ;

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
