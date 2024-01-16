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
public class ReadOrderDTO {

    private Long ono;                   // 주문번호
    private String memberID;            // 주문한 회원 
    private LocalDateTime orderDate;    // 주문 일자
    private String orderStatus;         // 주문 상태
    private List<ReadOrderProductDTO> orderProduct; // 주문 상품내역(상품 명과 수량 적시)
}
