package org.fktm.fastpickup.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class CartProductDTO {

    private Long cno;   // 장바구니 번호
    private Long pno;   // 상품 번호
    private int quantity; // 상품 개수 
}
