package org.fktm.fastpickup.review.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ReviewRegistDTO {

    private Long rno;                   // 리뷰 번호 pk
    private Long gno;                   // 대댓글을 위한 그룹번호 fk
    private String memberID;            // 회원 ID FK

    @NotBlank(message = "리뷰제목은 필수로 입력하여야 합니다.")
    private String reviewTitle;         // 리뷰 제목

    @NotBlank(message = "리뷰내용은 필수로 입력하여야 합니다.")
    private String reivewContent;       // 리뷰 내용
    
    @Builder.Default
    private List<String> imgsName = new ArrayList<>();  // 파일명
}
