package org.fktm.fastpickup.shoppingcart.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.shoppingcart.dto.CartProductDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartRequestDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.shoppingcart.mappers.CartMapper;
import org.fktm.fastpickup.shoppingcart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> createCart(
        @RequestBody CartRequestDTO cartRequestDTO
    ){

        log.info("===== /api/cart/add | Post =====");

        cartService.addCart(cartRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success AddItem"));
    }

}
