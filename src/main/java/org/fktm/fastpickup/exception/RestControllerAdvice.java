package org.fktm.fastpickup.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(FastPickUpException.class)
    public ResponseEntity<List<Map<String, String>>> handlerFastPickUpException(FastPickUpException e) {
        
        List<Map<String, String>> body = new ArrayList<>();

        body.add(Map.of("Error Code",e.getExceptionCode().getCode()));
        body.add(Map.of("Error Message",e.getExceptionCode().getMessage()));

        return ResponseEntity.status(e.getExceptionCode().getHttpStatus())
                             .body(body);
    }

}
