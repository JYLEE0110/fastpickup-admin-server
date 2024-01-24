package org.fktm.fastpickup.order.service;

import java.util.ArrayList;
import java.util.List;

import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.ListOrderDTO;
import org.fktm.fastpickup.order.dto.ModifyOrderStatusDTO;
import org.fktm.fastpickup.order.dto.OrderProductDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class OrderServiceTests {

    @Autowired(required = false)
    private OrderService orderService;

    private static final String TEST_MEMBER_ID = "admin";
    private static final Long TEST_ONO = 11L;
    private static final int TEST_QUANTITY = 3;
    private static final String TEST_ORDER_STATUS = "준비";

    private CreateOrderDTO createOrderDTO;
    private OrderProductDTO orderProductDTO1;
    private OrderProductDTO orderProductDTO2;
    private List<OrderProductDTO> orderProducts = new ArrayList<>();

    private ModifyOrderStatusDTO modifyOrderStatusDTO;

    private ReadOrderDTO readOrderDTO;

    private PageRequestDTO pageRequestDTO;

    @BeforeEach
    public void init() {

        orderProductDTO1 = OrderProductDTO.builder()
                .pno(45L)
                .quantity(TEST_QUANTITY)
                .build();

        orderProductDTO2 = OrderProductDTO.builder()
                .pno(31L)
                .quantity(TEST_QUANTITY)
                .build();

        orderProducts.add(orderProductDTO1);
        orderProducts.add(orderProductDTO2);

        createOrderDTO = CreateOrderDTO.builder()
                .memberID(TEST_MEMBER_ID)
                .orderProducts(orderProducts)
                .build();

        modifyOrderStatusDTO = ModifyOrderStatusDTO.builder()
                .ono(TEST_ONO)
                .orderStatus(TEST_ORDER_STATUS)
                .build();
        
        pageRequestDTO = PageRequestDTO.builder().build();

    }

    @DisplayName("주문생성 서비스 테스트")
    @Test
    // @Transactional
    public void createOrderService() {

        // GIVEN
        log.info("===== Start createOrderService Test =====");

        // WHEN
        orderService.createOrder(createOrderDTO);

        // THEN
        log.info("===== END createOrderService Test =====");

    }

    @DisplayName("주문상세 서비스 테스트")
    @Test
    // @Transactional
    public void readOrderService() {

        // GIVEN
        log.info("===== Start readOrderService Test =====");

        // WHEN
        readOrderDTO = orderService.readOrder(TEST_ONO);
        log.info(readOrderDTO);

        // THEN
        Assertions.assertNotNull(readOrderDTO, "해당 데이터가 없습니다.");
        log.info("===== END readOrderService Test =====");

    }


    @DisplayName("주문 상태변경 서비스 테스트")
    @Test
    // @Transactional
    public void modifyOrderStatusService() {

        // GIVEN
        log.info("===== Start modifyOrderStatusService Test =====");

        // WHEN
        orderService.modifyOrderStatus(modifyOrderStatusDTO);

        // THEN
        log.info("===== END modifyOrderStatusService Test =====");

    }


    @DisplayName("주문 리스트 서비스 테스트")
    @Test
    // @Transactional
    public void getOrderListService() {

        // GIVEN
        log.info("===== Start getOrderListService Test =====");

        // WHEN
        PageResponseDTO<ListOrderDTO> list=  orderService.getOrderList(pageRequestDTO);
        log.info(list);

        // THEN
        log.info("===== END getOrderListService Test =====");

    }

}
