package org.fktm.fastpickup.product.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotBlank(message = "상품명은 필수 입니다.")
    private String productName;       // 상품명
    private int productPrice;         // 상품 가격
    private String productContent;    // 상품 설명

    @Builder.Default
    private List<String> imgsName = new ArrayList<>();  // 파일명
}
