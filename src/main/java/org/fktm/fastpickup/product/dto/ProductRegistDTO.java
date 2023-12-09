package org.fktm.fastpickup.product.dto;

import java.util.ArrayList;
import java.util.List;

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
public class ProductRegistDTO {

    private Long pno;                 // 등록 후 selectKey로 pno 확인 가능
    private int cno;                  // 카테고리 번호
    private String productName;       // 상품명
    private int productPrice;         // 상품 가격
    private String productContent;    // 상품 설명

    @Builder.Default
    private List<String> imgsName = new ArrayList<>();  // 파일명
}
