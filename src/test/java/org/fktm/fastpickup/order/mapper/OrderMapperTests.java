package org.fktm.fastpickup.order.mapper;

import java.util.ArrayList;
import java.util.List;

import org.fktm.fastpickup.order.OrderStatus;
import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.CreateOrderProductDTO;
import org.fktm.fastpickup.order.dto.OrderProductDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.order.mappers.OrderMapper;
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
public class OrderMapperTests {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    private static final String TEST_MEMBER_ID = "admin";
    private static final int TEST_QUANTITY = 3;
    private static final Long TEST_ONO = 7L;
    private static final String TEST_ORDER_STATUS = "접수";

    private CreateOrderDTO createOrderDTO;

    private OrderProductDTO orderProductDTO1;
    private OrderProductDTO orderProductDTO2;
    private List<OrderProductDTO> orderProducts = new ArrayList<>();

    private CreateOrderProductDTO createOrderProductDTO;

    private ReadOrderDTO readOrderDTO;

    @BeforeEach
    public void init() {

        createOrderDTO = CreateOrderDTO.builder()
                .memberID(TEST_MEMBER_ID)
                .build();

    }

    @DisplayName("주문 생성 매퍼 테스트")
    @Test
    // @Transactional
    public void createOrder() {

        // GIVEN
        log.info("===== Start createORder Test =====");

        // WHEN
        int result = orderMapper.createOrder(createOrderDTO);
        Long ono = createOrderDTO.getOno();

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

        createOrderProductDTO = CreateOrderProductDTO.builder()
                .ono(ono)
                .orderProducts(orderProducts)
                .build();

        orderMapper.createOrderProduct(createOrderProductDTO);

        // THEN
        Assertions.assertEquals(result, 1, "주문 중에 예상치못한 오류가 발생하였습니다.");
        log.info("===== END createORder Test =====");
    }

    @DisplayName("주문 상세보기 매퍼 테스트")
    @Test
    @Transactional
    public void readOrder() {

        // GIVEN
        log.info("===== Start readOrder Test =====");

        // WHEN
        readOrderDTO = orderMapper.readOrder(TEST_ONO);
        log.info(readOrderDTO);

        //THEN
        Assertions.assertNotNull(readOrderDTO);
        log.info("===== END readOrder Test =====");

    }

    @DisplayName("주문 상태변경 매퍼 테스트")
    @Test
    // @Transactional
    public void modifyOrderStatus() {

        // GIVEN
        log.info("===== Start modifyOrderStatus Test =====");

        // WHEN
        orderMapper.modifyOrderStatus(TEST_ONO, TEST_ORDER_STATUS);
        readOrderDTO = orderMapper.readOrder(TEST_ONO);

        log.info(readOrderDTO);

        //THEN
        log.info("===== END modifyOrderStatus Test =====");

    }

}
