package org.fktm.fastpickup.review.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewModifyDTO;
import org.fktm.fastpickup.review.dto.ReviewReadDTO;
import org.fktm.fastpickup.review.dto.ReviewRegistDTO;
import org.fktm.fastpickup.review.service.ReviewService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewRestController {

    // 의존성 주입
    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/regist")
    public ResponseEntity<Map<String, String>> registReview(
            @Valid @RequestBody ReviewRegistDTO reviewRegistDTO) {

        log.info("===== /api/review/regist | Post =====");

        reviewService.registReview(reviewRegistDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("result", "Success RegistProduct"));
    }

    // 리뷰 상세
    @GetMapping("/read/{rno}")
    public ResponseEntity<ReviewReadDTO> readReview(
            @PathVariable("rno") Long rno) {

        log.info("===== /api/review/read | Get =====");

        ReviewReadDTO reviewReadDTO = reviewService.readReview(rno);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewReadDTO);
    }

    // 리뷰 답글 상세
    @GetMapping("/reply/read/{gno}")
    public ResponseEntity<ReviewReadDTO> readReply(
            @PathVariable("gno") Long gno) {

        log.info("===== /api/review/read | Get =====");

        ReviewReadDTO reviewReadDTO = reviewService.readReply(gno);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewReadDTO);
    }

    // 마이페이지 리뷰 리스트
    @GetMapping("/list/mypage/{memberID}")
    public ResponseEntity<PageResponseDTO<ReviewListDTO>> getReviewList(
            @PathVariable("memberID") String memberID,
            PageRequestDTO pageRequestDTO) {

        log.info("===== /api/review/list/mypage | Get =====");

        PageResponseDTO<ReviewListDTO> responseDTO = reviewService.getReviewList(pageRequestDTO, memberID);

        return ResponseEntity.status((HttpStatus.CREATED)).body(responseDTO);

    }

    // 상품상세 페이지 리뷰 리스트
    @GetMapping("/list/product/{pno}")
    public ResponseEntity<PageResponseDTO<ReviewListDTO>> getProductReviewList(
            @PathVariable("pno") Long pno,
            PageRequestDTO pageRequestDTO) {

        log.info("===== /api/review/list/product | Get =====");

        PageResponseDTO<ReviewListDTO> responseDTO = reviewService.getProductReviewList(pageRequestDTO, pno);

        return ResponseEntity.status((HttpStatus.CREATED)).body(responseDTO);

    }

    // 리뷰 삭제
    @PutMapping("/remove/{rno}")
    public ResponseEntity<Map<String, String>> removeReview(
            @PathVariable("rno") Long rno) {
        log.info("===== /api/reviwe/remove/{rno} | Get =====");

        reviewService.removeReview(rno);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("result", "success RemoveReview"));
    }

    // 리뷰 수정
    @PutMapping("/modify")
    public ResponseEntity<Map<String, String>> modifyReview(
            @RequestBody @Valid ReviewModifyDTO reviewModifyDTO) {
        log.info("===== /api/modify/review | Put =====");

        reviewService.modifyReview(reviewModifyDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("result", "SUCEESS MODIFY REVIEW"));
    }
}