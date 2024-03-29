package org.fktm.fastpickup.shoppingcart.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.shoppingcart.dto.CartListDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartProductDTO;
import org.fktm.fastpickup.shoppingcart.dto.CartRequestDTO;
import org.fktm.fastpickup.shoppingcart.dto.ShoppingCartDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;

public interface CartMapper {

    // 장바구니 생성
    int addCart(CartRequestDTO cartRequestDTO);

    // 장바구니 생성 후 상품 추가
    int addCartProduct(CartRequestDTO cartRequestDTO);

    // 장바구니 리스트
    List<CartListDTO> getCartList(@Param("pr") PageRequestDTO pageRequestDTO, @Param("memberID")String memberID);
    // 장바구니 페이징을 위한 카운트
    Long getCartTotal(@Param("pr")PageRequestDTO pageRequestDTO, @Param("memberID")String memberID);

    // 장바구니 삭제
    int removeItem(Long cno);

    // 장바구니 전체 삭제
    int removeAllItem(String memberID);
}
