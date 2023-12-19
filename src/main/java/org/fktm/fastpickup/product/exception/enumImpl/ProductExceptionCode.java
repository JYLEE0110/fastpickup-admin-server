package org.fktm.fastpickup.product.exception.enumImpl;

import org.fktm.fastpickup.exception.enumcode.ExceptionCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
public enum ProductExceptionCode implements ExceptionCode {

    NULL_PRODUCT_NAME(HttpStatus.BAD_REQUEST, "상품명 입력은 필수 입니다."),
    NULL_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "상품 가격입력은 필수 입니다."),
    NULL_PRODUCT_CONTENT(HttpStatus.BAD_REQUEST, "상품 설명 입력은 필수 입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    // 정의한 enum명
    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
