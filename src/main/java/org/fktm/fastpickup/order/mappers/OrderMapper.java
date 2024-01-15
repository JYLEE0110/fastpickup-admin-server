package org.fktm.fastpickup.order.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.ListOrderDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.order.dto.ReadOrderProductDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;

public interface OrderMapper {
    
    // 주문 생성
    int createOrder(CreateOrderDTO createOrderDTO);

    // 주문 상품 생성
    int createOrderProduct(List<Map<String, String>> orderProducts);

    // 주문 상세 내역
    ReadOrderDTO readOrder(Long ono);

    // 주문 상태 변경
    int modifyOrderStatus(@Param("ono")Long ono, @Param("orderStatus")String orderStatus);

    // 주문 리스트
    List<ListOrderDTO> getOrderList(PageRequestDTO pageRequestDTO);

}
