package org.fktm.fastpickup.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {

    ORDER_CONFIRMATION("주문확인"),
    ACCEPTANCE("접수"),
    COMPLETION("완료"),
    CANCELLED("취소");

    private final String value;

    public String getValue() {
        return value;
    }

    // 모든 enum을 순회하면서 사용자가 입력한 value(주문확인, 접수...)와 일치하는 것을 반환
    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.value.equals(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid order status: " + status);
    }

}
