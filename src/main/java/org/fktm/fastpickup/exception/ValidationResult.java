package org.fktm.fastpickup.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.fktm.fastpickup.exception.response.ExceptionResponse;
import org.springframework.validation.Errors;

public class ValidationResult {
    
    private final List<ExceptionResponse> errors;

    public ValidationResult(Errors errors){

        this.errors = errors.getFieldErrors()
                            .stream()
                            .map(error -> new ExceptionResponse(error))
                            .collect(Collectors.toList());

    }

    public List<ExceptionResponse> getErrors(){
        return errors;
    }


}
