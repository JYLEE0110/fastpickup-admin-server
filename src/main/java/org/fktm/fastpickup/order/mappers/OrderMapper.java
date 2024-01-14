package org.fktm.fastpickup.order.mappers;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.CreateOrderProductDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.order.dto.ReadOrderProductDTO;

public interface OrderMapper {
    
    // 주문 생성
    int createOrder(CreateOrderDTO createOrderDTO);

    // 주문 상품 생성
    int createOrderProduct(CreateOrderProductDTO createOrderProductDTO);

    // 주문 상세 내역
    ReadOrderDTO readOrder(Long ono);

    // 주문 상태 변경
    int modifyOrderStatus(@Param("ono")Long ono, @Param("orderStatus")String orderStatus);

}
