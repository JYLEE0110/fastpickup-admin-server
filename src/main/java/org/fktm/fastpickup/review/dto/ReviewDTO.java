package org.fktm.fastpickup.review.dto;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ReviewDTO {

    private Long rno;                   // 리뷰 번호 pk
    private Long gno;                   // 대댓글을 위한 그룹번호 fk
    private String memberID;            // 회원 ID FK
    private Long pno;                   // 상품번호

    @NotBlank(message = "리뷰제목은 필수로 입력하여야 합니다.")
    private String reviewTitle;         // 리뷰 제목

    @NotBlank(message = "리뷰내용은 필수로 입력하여야 합니다.")
    private String reivewContent;       // 리뷰 내용
    
    private LocalDateTime registDate;   // 리뷰 작성일자
    private LocalDateTime modifyDateTime; // 리뷰 수정일자
    private boolean isDeleted;           // 삭제 여부
}
