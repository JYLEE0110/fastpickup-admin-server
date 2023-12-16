package org.fktm.fastpickup.exception;

import lombok.RequiredArgsConstructor;

public class FastPickUpException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public FastPickUpException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

}
