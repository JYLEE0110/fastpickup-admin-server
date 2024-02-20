package org.fktm.fastpickup.shoppingcart.mapper;

import org.fktm.fastpickup.shoppingcart.dto.CartProuctDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.shoppingcart.mappers.CartMapper;
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
public class CartMapperTests {
    
    @Autowired(required = false)
    private CartMapper cartMapper;


    private static final String TEST_MEMBER_ID = "Admin";
    private static final Long TEST_CNO = 2L;
    private static final Long TEST_PNO = 31L;
    private static final int TEST_QUANTITY = 3;

    private ShoppingCartDTO shoppingCartDTO;
    private CartProuctDTO cartProuctDTO;

    @BeforeEach
    public void init(){

        shoppingCartDTO = ShoppingCartDTO.builder()
                                        .memberID(TEST_MEMBER_ID)
                                        .build();
        
        cartProuctDTO = CartProuctDTO.builder()
                                    .pno(TEST_PNO)
                                    .cno(TEST_CNO)
                                    .quantity(TEST_QUANTITY)
                                    .build();
    }


    @DisplayName("장바구니 생성 매퍼 테스트")
    // @Transactional
    @Test
    public void createCart(){

        // GIVEN
        log.info("===== Start Create Cart Mapper Test=====");

        // WHEN
        int result = cartMapper.addCart(shoppingCartDTO);

        // THEN
        Assertions.assertEquals(result, 1,"장바구니 생성에 실패 하였습니다.");

    }

    @DisplayName("장바구니 상품 생성 매퍼 테스트")
    // @Transactional
    @Test
    public void createCartProduct(){

        // GIVEN
        log.info("===== Start createCartProduct Mapper Test=====");

        // WHEN
        int result = cartMapper.addCartProduct(cartProuctDTO);

        // THEN
        Assertions.assertEquals(result, 1,"장바구니 생성에 실패 하였습니다.");

    }

}
