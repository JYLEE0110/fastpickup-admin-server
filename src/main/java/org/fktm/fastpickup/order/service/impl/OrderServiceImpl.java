package org.fktm.fastpickup.order.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.ListOrderDTO;
import org.fktm.fastpickup.order.dto.ModifyOrderStatusDTO;
import org.fktm.fastpickup.order.dto.OrderProductDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.order.mappers.OrderMapper;
import org.fktm.fastpickup.order.service.OrderService;
import org.fktm.fastpickup.shoppingcart.mappers.CartMapper;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;

    // 주문 생성 서비스
    @Override
    public void createOrder(CreateOrderDTO createOrderDTO) {

        log.info("=== createOrder Service ===");

        // 주문 생성
        orderMapper.createOrder(createOrderDTO);

        // 주문 생성 후 주문번호 추출
        Long ono = createOrderDTO.getOno();

        List<OrderProductDTO> orderProduct = createOrderDTO.getOrderProducts();

        List<Map<String, String>> orderProducts = orderProduct.stream().map(dto -> {
            Long pno = dto.getPno();
            int quantity = dto.getQuantity();

            return Map.of("pno", String.valueOf(pno), "quantity", String.valueOf(quantity), "ono", String.valueOf(ono));
        }).collect(Collectors.toList());
        orderMapper.createOrderProduct(orderProducts);

        // 맴버 아이디 추출 후 
        String memberID = createOrderDTO.getMemberID();

        // 장바구니 비우기
        cartMapper.removeAllItem(memberID);

    }

    // 주문 상세
    @Override
    public ReadOrderDTO readOrder(Long ono) {

        log.info("=== readOrder Service ===");

        return orderMapper.readOrder(ono);

    }

    // 주문 상태 변경
    @Override
    public void modifyOrderStatus(ModifyOrderStatusDTO modifyOrderStatusDTO) {

        log.info("=== modifyOrderStatus Service ===");

        orderMapper.modifyOrderStatus(modifyOrderStatusDTO);

    }

    // 주문목록
    @Override
    public PageResponseDTO<ListOrderDTO> getOrderList(PageRequestDTO pageRequestDTO, String memberID) {

        log.info("=== getOrderList Service ===");

        List<ListOrderDTO> orderList = orderMapper.getOrderList(pageRequestDTO, memberID);
        Long totalOrderList = orderMapper.getOrderTotal(pageRequestDTO, memberID);

        return PageResponseDTO.<ListOrderDTO> withAll()
                        .list(orderList)
                        .total(totalOrderList)
                        .pageRequestDTO(pageRequestDTO)
                        .build();

    }

}
