package org.fktm.fastpickup.review.mappers;

import org.fktm.fastpickup.review.dto.ReviewReadDTO;
import org.fktm.fastpickup.review.dto.ReviewRegistDTO;

public interface ReviewMapper {
    
    // 리뷰 등록
    int registReview(ReviewRegistDTO reviewRegistDTO);
    // 대댓글(리뷰) 그룹번호 업데이트
    int updateGno(Long rno);

    // 대댓글(리뷰) 등록
    int registChildReview(ReviewRegistDTO reviewRegistDTO);

    // 리뷰 상세보기
    ReviewReadDTO readReview(Long rno);

}
