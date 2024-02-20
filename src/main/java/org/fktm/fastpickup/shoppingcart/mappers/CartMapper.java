package org.fktm.fastpickup.shoppingcart.mappers;

import org.fktm.fastpickup.shoppingcart.dto.CartProuctDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;

public interface CartMapper {

    // 장바구니 생성
    int addCart(ShoppingCartDTO shoppingCartDTO);

    // 장바구니 생성 후 상품 추가
    int addCartProduct(CartProuctDTO cartProuctDTO);
}
