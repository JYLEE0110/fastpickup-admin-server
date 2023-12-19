package org.fktm.fastpickup.exception.response;

import java.net.BindException;

import org.fktm.fastpickup.exception.enumcode.ExceptionCode;
import org.springframework.http.HttpStatus;
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

    public ExceptionResponse(ExceptionCode exceptionCode){
    
        this.httpStatus = exceptionCode.getHttpStatus();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    
    }

    public ExceptionResponse(FieldError error){
        
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = error.getCode() + "_"+ error.getField();
        this.message = error.getDefaultMessage();
    }

}
