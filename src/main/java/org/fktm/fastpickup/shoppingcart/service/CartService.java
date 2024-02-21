package org.fktm.fastpickup.shoppingcart.service;

import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProuctDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CartService {
    
    // 장바구니 추가
    void addCart(ShoppingCartDTO shoppingCartDTO, CartProuctDTO cartProductDTO);

    // 장바구니 리스트
    PageResponseDTO<CartListDTO> getCartList(PageRequestDTO pageRequestDTO, String memberID);

    // 장바구니 삭제
    void removeCartItem(Long cno);

}
