package org.fktm.fastpickup.review.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.review.dto.ReviewListDTO;
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
        @PathVariable("rno") Long rno
    ){

        log.info("===== /api/review/read | Get =====");

        ReviewReadDTO reviewReadDTO = reviewService.readReview(rno);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewReadDTO);
    }

    // 리뷰 리스트
    @GetMapping("/list/{memberID}")
    public ResponseEntity<PageResponseDTO<ReviewListDTO>> getReviewList(
        @PathVariable("memberID")String memberID,
        PageRequestDTO pageRequestDTO 
        ){

        log.info("===== /api/review/list | Get =====");

        PageResponseDTO<ReviewListDTO> responseDTO= reviewService.getReviewList(pageRequestDTO, memberID);

        return ResponseEntity.status((HttpStatus.CREATED)).body(responseDTO);

    }
    
}
