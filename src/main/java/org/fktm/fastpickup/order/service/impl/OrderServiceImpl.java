package org.fktm.fastpickup.order.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.OrderProductDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.order.mappers.OrderMapper;
import org.fktm.fastpickup.order.service.OrderService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

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

    }

    @Override
    public ReadOrderDTO readOrder(Long ono) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readOrder'");
    }

    @Override
    public void modifyOrderStatus(Long ono, String orderStatus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyOrderStatus'");
    }

}
