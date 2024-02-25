package org.fktm.fastpickup.shoppingcart.restcontroller;

import java.util.List;
import java.util.Map;

import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProductDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartRequestDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.shoppingcart.mappers.CartMapper;
import org.fktm.fastpickup.shoppingcart.service.CartService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
@Log4j2
public class CartRestController {

    private final CartService cartService;

    // 장바구니 상품 추가
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> createCart(
        @RequestBody CartRequestDTO cartRequestDTO
    ){

        log.info("===== /api/cart/add | Post =====");

        cartService.addCart(cartRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success AddItem"));
    }

    // 장바구니 리스트 목록
    @GetMapping("/list/{memberID}")
    public ResponseEntity<PageResponseDTO<CartListDTO>> getCartList(
        @PathVariable("memberID") String memberID,
        PageRequestDTO pageRequestDTO
    ){

        log.info("===== /api/cart/list | GET  =====");

        PageResponseDTO<CartListDTO> list = cartService.getCartList(pageRequestDTO, memberID);

        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // 장바구니 삭제
    @DeleteMapping("/remove/{cno}")
    public ResponseEntity<Map<String, Long>> removeCartItem(
        @PathVariable("cno") Long cno
    ){
        log.info("===== /api/cart/remove | DELETE  =====");

        cartService.removeCartItem(cno);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("SuccessRemoveCartItem",cno));

    }

}
