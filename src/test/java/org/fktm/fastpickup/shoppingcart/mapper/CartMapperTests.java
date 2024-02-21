package org.fktm.fastpickup.shoppingcart.mapper;

import java.util.List;

import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProuctDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.shoppingcart.mappers.CartMapper;
import org.fktm.fastpickup.util.page.PageRequestDTO;
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
    private static final Long TEST_CNO = 3L;
    private static final Long TEST_PNO = 31L;
    private static final int TEST_QUANTITY = 3;

    private ShoppingCartDTO shoppingCartDTO;
    private CartProuctDTO cartProuctDTO;
    private PageRequestDTO pageRequestDTO;

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

        pageRequestDTO = PageRequestDTO.builder().build();
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

    @DisplayName("장바구니 리스트 매퍼 테스트")
    // @Transactional
    @Test
    public void getCartList(){

        // GIVEN
        log.info("===== Start getCartList Mapper Test=====");

        // WHEN
        List<CartListDTO> list = cartMapper.getCartList(pageRequestDTO, TEST_MEMBER_ID);

        // THEN
        log.info(list);
        Assertions.assertNotNull(list,"리스트가 비어있습니다.");
    }

    @DisplayName("장바구니 리스트 페이징을 위한 총 개수 매퍼 테스트")
    // @Transactional
    @Test
    public void getCartListTotal(){
        // GIVEN
        log.info("===== Start getCartListTotal Mapper Test=====");

        // WHEN
        Long total = cartMapper.getCartTotal(pageRequestDTO, TEST_MEMBER_ID);

        // THEN
        log.info(total);
        // Assertions.assertNotNull(total,"리스트가 비어있습니다.");
    }

    @DisplayName("장바구니 삭제 매퍼 테스트")
    // @Transactional
    @Test
    public void removeItem(){

        // GIVEN
        log.info("===== Start removeItem Mapper Test=====");

        // WHEN
        int result = cartMapper.removeItem(TEST_CNO);

        // THEN
        Assertions.assertEquals(1, result, "삭제가 정상적으로 이루어지지 않았습니다.");
    }
}
