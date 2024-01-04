/* package org.fktm.fastpickup.security.handler.failurecode;

import org.fktm.fastpickup.exception.enumcode.ExceptionCode;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LoginFailureCode implements ExceptionCode {

    BadCredentialsException(HttpStatus.NOT_ACCEPTABLE, "비밀번호불일치"),
    UsernameNotFoundException(HttpStatus.NOT_FOUND, "계정없음"),
    AccountExpiredException(HttpStatus.FORBIDDEN, "계정만료"),
    CredentialsExpiredException(HttpStatus.FORBIDDEN, "비밀번호만료"),
    DisabledException(HttpStatus.FORBIDDEN, "계정비활성화"),
    LockedException(HttpStatus.FORBIDDEN, "계정잠김"),
    NoneException(HttpStatus.INTERNAL_SERVER_ERROR, "알수없는 에러");

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
 */