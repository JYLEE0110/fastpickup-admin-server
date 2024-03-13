package org.fktm.fastpickup.review.service;

import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewReadDTO;
import org.fktm.fastpickup.review.dto.ReviewRegistDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReviewService {

    // 리뷰 등록
    void registReview(ReviewRegistDTO reviewRegistDTO);

    // 리뷰 상세보기
    ReviewReadDTO readReview(Long rno);

    // 리뷰 리스트
    PageResponseDTO<ReviewListDTO> getReviewList(PageRequestDTO pageRequestDTO);
    
}
