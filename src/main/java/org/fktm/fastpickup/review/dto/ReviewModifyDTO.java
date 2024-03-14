package org.fktm.fastpickup.review.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
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
public class ReviewModifyDTO {

    private Long rno;

    @NotBlank(message = "리뷰제목은 필수로 입력하여야 합니다.")
    private String reviewTitle;

    @NotBlank(message = "리뷰내용은 필수로 입력하여야 합니다.")
    private String reviewContent;
    private List<String> imgsName;
    
}
