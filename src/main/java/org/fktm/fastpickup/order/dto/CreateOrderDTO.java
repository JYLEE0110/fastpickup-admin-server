package org.fktm.fastpickup.order.dto;

import java.util.List;

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
public class CreateOrderDTO {

    private Long ono;
    private String memberID;
    private List<OrderProductDTO> orderProducts;// pno와 수량 quantity를 담은 List
    
}
