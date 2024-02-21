package org.fktm.fastpickup.shoppingcart.service.impl;

import java.util.List;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProuctDTO;
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
    public void addCart(ShoppingCartDTO shoppingCartDTO, CartProuctDTO cartProductDTO) {

        log.info("===== addCart Service =====");

        // 장바구니 생성
        cartMapper.addCart(shoppingCartDTO);
        Long cno = shoppingCartDTO.getCno();

        cartProductDTO.setCno(cno);

        // 장바구니 생성 후 상품 추가
        cartMapper.addCartProduct(cartProductDTO);

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

    // 카트 아이템 삭제
    @Override
    public void removeCartItem(Long cno) {

        log.info("===== removeCart Service =====");

        cartMapper.removeItem(cno);

    }

}
