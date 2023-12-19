package org.fktm.fastpickup.exception.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fktm.fastpickup.exception.ValidationResult;
import org.fktm.fastpickup.exception.customexception.FastPickUpException;
import org.fktm.fastpickup.exception.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    // 커스텀 Exception Handler
    @ExceptionHandler(FastPickUpException.class)

    public ResponseEntity<ExceptionResponse> handlerFastPickUpException(FastPickUpException e) {
        
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getExceptionCode()) ;

        return ResponseEntity.status(e.getExceptionCode().getHttpStatus())
                             .body(exceptionResponse);
    }

    // Validation API ExceptionHandler
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ExceptionResponse> handlerBindException(BindException e) {
        
        ValidationResult validationResult = new ValidationResult(e);
        ExceptionResponse exceptionResponse = validationResult.getErrors().get(0);

        return ResponseEntity.status(exceptionResponse.getHttpStatus())
                             .body(exceptionResponse);
    }

}
