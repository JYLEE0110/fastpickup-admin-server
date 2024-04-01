package org.fktm.fastpickup.shoppingcart.service;

import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProductDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartRequestDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
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
public class CartServiceTests {

    @Autowired(required = false)
    private CartService cartService;

    private static final String TEST_MEMBER_ID = "Admin";
    private static final Long TEST_CNO = 3L;
    private static final Long TEST_PNO = 31L;
    private static final int TEST_QUANTITY = 3;

    private CartProductDTO cartProductDTO;
    private ShoppingCartDTO shoppingCartDTO;
    private PageRequestDTO pageRequestDTO;
    private CartRequestDTO cartRequestDTO;

    @BeforeEach
    public void init() {

        shoppingCartDTO = ShoppingCartDTO.builder()
                .memberID(TEST_MEMBER_ID)
                .build();

        cartProductDTO = CartProductDTO.builder()
                // .cno(TEST_CNO)
                .pno(TEST_PNO)
                .quantity(TEST_QUANTITY)
                .build();

        cartRequestDTO = CartRequestDTO.builder()
                .cartProductDTO(cartProductDTO)
                .shoppingCartDTO(shoppingCartDTO)
                .build();

        pageRequestDTO = PageRequestDTO.builder().build();

    }

    @Test
    @DisplayName("장바구니 추가 생성 서비스 테스트")
    @Transactional
    public void createCart() {

        // GIVEN
        log.info("===== Start Create Cart Service Test=====");

        // WHEN
        cartService.addCart(cartRequestDTO);

        // THEN
        // Assertions.assertEquals(result, 1,"장바구니 생성에 실패 하였습니다.");

    }

    @Test
    @DisplayName("장바구니 리스트 서비스 테스트")
    @Transactional
    public void getCartList() {

        // GIVEN
        log.info("===== Start getCartList Service Test=====");

        // WHEN
        PageResponseDTO<CartListDTO> list = cartService.getCartList(pageRequestDTO, TEST_MEMBER_ID);

        // THEN
        log.info(list);
        Assertions.assertNotNull(list, "리스트를 불러오지 못했습니다.");

    }

    @Test
    @DisplayName("장바구니 아이템 삭제 서비스 테스트")
    @Transactional
    public void removeCartItem() {

        // GIVEN
        log.info("===== Start removeCartItem Service Test=====");

        // WHEN
        cartService.removeCartItem(TEST_CNO);

        // THEN
        // log.info(list);
        // Assertions.assertNotNull(list, "리스트를 불러오지 못했습니다.");

    }
}
