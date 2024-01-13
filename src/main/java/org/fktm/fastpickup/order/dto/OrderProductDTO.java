package org.fktm.fastpickup.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderProductDTO {
    private Long pno;       // 상품번호 pk
    private int quantity;   // 주문 개수
}
