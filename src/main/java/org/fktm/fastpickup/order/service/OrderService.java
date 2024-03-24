package org.fktm.fastpickup.order.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.ListOrderDTO;
import org.fktm.fastpickup.order.dto.ModifyOrderStatusDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService {
    
    // 주문 생성
    void createOrder(CreateOrderDTO createOrderDTO);

    // 주문 상세 내역
    ReadOrderDTO readOrder(Long ono);

    // 주문 상태 변경
    void modifyOrderStatus(ModifyOrderStatusDTO modifyOrderStatusDTO);

    // 주문 리스트
    PageResponseDTO<ListOrderDTO> getOrderList(PageRequestDTO pageRequestDTO, String memberID);

}
