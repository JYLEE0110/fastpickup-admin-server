package org.fktm.fastpickup.exception.response;

import org.fktm.fastpickup.exception.enumcode.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/* 예외처리한 반환값을 담아주는 class => RestControllerAdvice의 ExceptionHanler에서 처리 */

@Getter
public class ExceptionResponse {
    
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    // 커스텀 Exception 생성자
    // 전달 받은 ExceptionCode는 enum의 상위 인터페이스이므로 모든 enum클래스를 받을 수 있다.
    public ExceptionResponse(ExceptionCode exceptionCode){
    
        this.httpStatus = exceptionCode.getHttpStatus();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    
    }

    // Validation API 생성자
    public ExceptionResponse(FieldError error){
        
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = error.getCode() + "_"+ error.getField();
        this.message = error.getDefaultMessage();
    }

    public ExceptionResponse(BindException bindException){
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = bindException.getFieldError().getCode() + "_" + bindException.getFieldError().getField();
        this.message = bindException.getFieldError().getDefaultMessage();
    }

}
