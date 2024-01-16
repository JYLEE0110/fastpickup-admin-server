package org.fktm.fastpickup.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ModifyOrderStatusDTO {

    private Long ono;           // 주문번호
    private String orderStatus; // 주문 상태
    
}
