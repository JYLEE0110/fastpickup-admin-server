package org.fktm.fastpickup.product.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    
    @NotBlank(message = "상품명은 필수로 입력하여야 합니다.")
    private String productName;       // 상품명

    @NotNull(message = "상품가격은 필수로 입력하여야 합니다.")
    @Positive(message = "상품 가격은 양수여야 합니다.")
    private Integer productPrice;     // 상품 가격

    @NotBlank(message = "상품설명은 필수로 입력하여야 합니다.")
    @Size(min = 5, max = 50, message ="글자 수는 5이상 50이하여야 합니다.")
    private String productContent;    // 상품 설명

    @Builder.Default
    private List<String> imgsName = new ArrayList<>();  // 파일명
}
