package org.fktm.fastpickup.order.mappers;

import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.CreateOrderProductDTO;

public interface OrderMapper {
    
    // 주문 생성
    int createOrder(CreateOrderDTO createOrderDTO);

    // 주문 상품 생성
    int createOrderProduct(CreateOrderProductDTO createOrderProductDTO);

}
