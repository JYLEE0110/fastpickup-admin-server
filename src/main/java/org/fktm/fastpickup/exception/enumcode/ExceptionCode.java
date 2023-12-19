package org.fktm.fastpickup.exception.enumcode;

import org.springframework.http.HttpStatus;

// 각 패키지 enum에 대한 상위 인터페이스 선언 
// => 비즈니스 로직에서 throw new CustomException(매개변수) 매개변수를 어느 타입으로든 받기위하여(다형성 만족)

public interface ExceptionCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
