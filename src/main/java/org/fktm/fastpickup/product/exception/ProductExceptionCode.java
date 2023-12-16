package org.fktm.fastpickup.product.exception;

import org.fktm.fastpickup.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductExceptionCode implements ExceptionCode {

    MISSING_NAME(HttpStatus.BAD_REQUEST, "PRODUCT_001", "상품명 입력은 필수 입니다."),
    MISSING_PRICE(HttpStatus.BAD_REQUEST, "PRODUCT_002", "상품 가격입력은 필수 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }




    
}
