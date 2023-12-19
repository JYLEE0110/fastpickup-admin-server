package org.fktm.fastpickup.exception.customexception;

import org.fktm.fastpickup.exception.enumcode.ExceptionCode;

import lombok.RequiredArgsConstructor;

/* 해당 프로젝트의 커스텀 Exception => 여러 Exception을 만들필요없이 하나만 생성 하고
 * enum으로 구체적인 정보를 반환한다.
 */

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
