package org.fktm.fastpickup.review.mappers;

import org.fktm.fastpickup.review.dto.ReviewRegistDTO;

public interface ReviewMapper {
    
    // 리뷰 등록
    int registReview(ReviewRegistDTO reviewRegistDTO);

}
