package org.fktm.fastpickup.review.mappers;

import java.util.List;

import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewReadDTO;
import org.fktm.fastpickup.review.dto.ReviewRegistDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;

public interface ReviewMapper {
    
    // 리뷰 등록
    int registReview(ReviewRegistDTO reviewRegistDTO);
    // 대댓글(리뷰) 그룹번호 업데이트
    int updateGno(Long rno);

    // 대댓글(리뷰) 등록
    int registChildReview(ReviewRegistDTO reviewRegistDTO);

    // 리뷰 상세보기
    ReviewReadDTO readReview(Long rno);

    // 리뷰 리스트
    List<ReviewListDTO> getReveiwList(PageRequestDTO pageRequestDTO);
    Long getTotal(PageRequestDTO pageRequestDTO);
}
