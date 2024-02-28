package org.fktm.fastpickup.shoppingcart.service;

import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProductDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartRequestDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CartService {
    
    // 장바구니 추가
    void addCart(CartRequestDTO cartRequestDTO);

    // 장바구니 리스트
    PageResponseDTO<CartListDTO> getCartList(PageRequestDTO pageRequestDTO, String memberID);

    // 장바구니 삭제
    void removeCartItem(Long cno);

    // 장바구니 전체 삭제
    void removeAllItem(String memberID);

}
