package org.fktm.fastpickup.product.dto;

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
public class ProductModifyDTO {

    private String productName;       // 상품명
    private int productPrice;         // 상품 가격
    private String productContent;    // 상품 설명
    private String imgName;           // 상품 이미지명
    
}
