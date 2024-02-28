package org.fktm.fastpickup.order.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.TituloEleitoral;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class ListOrderDTO {

    private Long ono;                   // 주문번호
    private String memberID;            // 주문한 회원 ID
    private LocalDateTime orderDate;    // 주문시간
    private String orderStatus;         // 주문상태
    private String productName;         // 주문상품 명
    private int quantity;               // 주문상품 총 수량
    private String imgName;

}
