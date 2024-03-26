package org.fktm.fastpickup.order.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.order.dto.CreateOrderDTO;
import org.fktm.fastpickup.order.dto.ListOrderDTO;
import org.fktm.fastpickup.order.dto.ModifyOrderStatusDTO;
import org.fktm.fastpickup.order.dto.ReadOrderDTO;
import org.fktm.fastpickup.order.service.OrderService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderRestController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Map<String, String>> createOrder(
            @RequestBody CreateOrderDTO createOrderDTO) {

        log.info("/api/order/create | POST");

        orderService.createOrder(createOrderDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success Received Order"));
    }

    // 주문 상세 보기
    @GetMapping("/read/{ono}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ReadOrderDTO> readOrder(
            @PathVariable("ono") Long ono) {

        log.info("/api/read/ | GET");

        ReadOrderDTO readOrderDTO = orderService.readOrder(ono);

        return ResponseEntity.status(HttpStatus.CREATED).body(readOrderDTO);

    }

    // 주문 상태 변경
    @PostMapping("/modify/status")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String,String>> modifyOrderStatus(
        @RequestBody ModifyOrderStatusDTO modifyOrderStatusDTO
    ){
        log.info("/api/modify/status | GET");

        orderService.modifyOrderStatus(modifyOrderStatusDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("result", "SUCESS MODIfY ORDER STATUS"));

    }

    // 주문 목록
    @GetMapping("/list/{memberID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<PageResponseDTO<ListOrderDTO>> getOrderList(
        PageRequestDTO pageRequestDTO,
        @PathVariable("memberID") String memberID
    ){
        log.info("/api/orderList | GET");

        PageResponseDTO<ListOrderDTO> list = orderService.getOrderList(pageRequestDTO, memberID);

        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }


}
