package org.fktm.fastpickup.review.dto;

import java.time.LocalDateTime;
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
@Builder
@ToString
public class ReviewReadDTO {

    private Long rno;                   // 리뷰 번호
    private Long pno;                 // 상품 번호
    private String memberID;            // 작성자 ID
    private String productName;         // 상품 명
    private String reviewTitle;         // 리뷰 제목
    private String reviewContent;       // 리뷰 내용
    private LocalDateTime registDate;   // 리뷰 작성일

    private List<String> imgsName;
    
}
