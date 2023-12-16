package org.fktm.fastpickup.exception;

import org.springframework.http.HttpStatus;

// 각 패키지 enum에 대한 인터페이스 선언
public interface ExceptionCode {
    String getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
