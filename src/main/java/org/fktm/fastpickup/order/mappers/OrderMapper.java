package org.fktm.fastpickup.order.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.ListOrderDTO;
import org.fktm.fastpickup.order.dto.ModifyOrderStatusDTO;
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
    int modifyOrderStatus(ModifyOrderStatusDTO modifyOrderStatusDTO );

    // 주문 리스트
    List<ListOrderDTO> getOrderList(@Param("pr")PageRequestDTO pageRequestDTO, @Param("memberID")String memberID);
    // 현재 페이지에 대한 주문목록 총 개수 
    Long getOrderTotal(@Param("pr")PageRequestDTO pageRequestDTO, @Param("memberID")String memberID);

}
