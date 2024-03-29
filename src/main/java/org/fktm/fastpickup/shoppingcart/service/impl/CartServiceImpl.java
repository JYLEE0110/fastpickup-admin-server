package org.fktm.fastpickup.shoppingcart.service.impl;

import java.util.List;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProductDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartRequestDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.shoppingcart.mappers.CartMapper;
import org.fktm.fastpickup.shoppingcart.service.CartService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;

    // 장바구니 추가
    @Override
    public void addCart(CartRequestDTO cartRequestDTO) {

        log.info("===== addCart Service =====");

        // 장바구니 생성
        cartMapper.addCart(cartRequestDTO);
        Long cno = cartRequestDTO.getShoppingCartDTO().getCno();

        cartRequestDTO.getCartProductDTO().setCno(cno);

        // 장바구니 생성 후 상품 추가
        cartMapper.addCartProduct(cartRequestDTO);

    }

    // 장바구니 리스트 목록
    @Override
    public PageResponseDTO<CartListDTO> getCartList(PageRequestDTO pageRequestDTO, String memberID) {

        log.info("===== getCartList Service =====");

        List<CartListDTO> list = cartMapper.getCartList(pageRequestDTO, memberID);
        Long total = cartMapper.getCartTotal(pageRequestDTO, memberID);

        return PageResponseDTO.<CartListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // 장바구니 아이템 전체 삭제
    @Override
    public void removeCartItem(Long cno) {

        log.info("===== removeCart Service =====");

        cartMapper.removeItem(cno);

    }

    // 장바구니 아이템 전체 삭제
    @Override
    public void removeAllItem(String memberID) {

        log.info("===== removeAllItem Service =====");

        cartMapper.removeAllItem(memberID);

    }

}
