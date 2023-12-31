package org.fktm.fastpickup.product.dto;

import java.time.LocalDateTime;

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
public class ProductDTO {
    
    private Long pno;                 // 상품 번호
    private int cno;                  // 카테고리 번호
    private String productName;       // 상품명
    private int productPrice;         // 상품 가격
    private String productContent;    // 상품 설명
    private LocalDateTime registDate; // 상품 등록일자
    private LocalDateTime modifyDate; // 상품 수정일자
    private Long viewCnt;             // 조회 수
    private boolean isDeleted;        // 삭제 여부
    private String imgName;           // 상품 이미지명
}
