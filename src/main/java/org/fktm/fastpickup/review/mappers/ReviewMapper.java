package org.fktm.fastpickup.review.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewModifyDTO;
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
    // 리뷰 답글 상세보기
    ReviewReadDTO readReply(Long gno);

    // 리뷰 리스트(마이페이지)
    List<ReviewListDTO> getReviewList(@Param("pr")PageRequestDTO pageRequestDTO, @Param("memberID") String memberID);
    Long getTotal(@Param("pr")PageRequestDTO pageRequestDTO, @Param("memberID") String memberID);

    // 리뷰 리스트(상품페이지)
    List<ReviewListDTO> getProductReviewList(@Param("pr")PageRequestDTO pageRequestDTO, @Param("pno") Long pno);
    Long getProductReviewTotal(@Param("pr")PageRequestDTO pageRequestDTO, @Param("pno") Long memberID);

    // 리뷰 삭제
    int removeReview(Long rno);

    // 리뷰 수정
    int modifyReview(ReviewModifyDTO modifyDTO);
}
