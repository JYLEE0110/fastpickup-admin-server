package org.fktm.fastpickup.review.service;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewModifyDTO;
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
    // 리뷰에 대한 답글 상세보기
    ReviewReadDTO readReply(Long gno);

    // 마이페이지 리뷰 리스트
    PageResponseDTO<ReviewListDTO> getReviewList(PageRequestDTO pageRequestDTO, String memberID);
    // 상품상세페이지 리뷰 리스트
    PageResponseDTO<ReviewListDTO> getProductReviewList(PageRequestDTO pageRequestDTO, Long pno);


    // 리뷰 삭제
    void removeReview(Long rno);

    // 리뷰 수정
    void modifyReview(ReviewModifyDTO reviewModifyDTO);
    
}
