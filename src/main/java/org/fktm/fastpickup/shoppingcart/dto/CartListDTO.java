package org.fktm.fastpickup.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class CartListDTO {
    private Long cno;
    private String memberID;
    private Long pno;
    private String productName;
    private int productPrice;
    private int quantity; 
}
