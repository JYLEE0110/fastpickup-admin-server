package org.fktm.fastpickup.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartRequestDTO {
    private ShoppingCartDTO shoppingCartDTO;
    private CartProductDTO cartProductDTO;
}
