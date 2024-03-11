package org.fktm.fastpickup.review.service;

import org.fktm.fastpickup.review.dto.ReviewRegistDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReviewService {

    // 리뷰 등록
    void registReview(ReviewRegistDTO reviewRegistDTO);
    
}
