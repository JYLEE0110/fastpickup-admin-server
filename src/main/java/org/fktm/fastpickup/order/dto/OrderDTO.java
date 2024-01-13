package org.fktm.fastpickup.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class OrderDTO {
    
    private Long ono;               // 주문 번호 PK
    private Long pno;               // 구매 상품번호 fk
    private String memberID;        // 회원 ID FK
    private int quantity;           // 수량
    private LocalDateTime orderDate;// 주문 일자
    private String orderStatus;     // 주문 상태

}
