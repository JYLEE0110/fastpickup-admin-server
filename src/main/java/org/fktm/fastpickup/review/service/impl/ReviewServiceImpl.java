package org.fktm.fastpickup.review.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewReadDTO;
import org.fktm.fastpickup.review.dto.ReviewRegistDTO;
import org.fktm.fastpickup.review.mappers.ReviewImgMapper;
import org.fktm.fastpickup.review.mappers.ReviewMapper;
import org.fktm.fastpickup.review.service.ReviewService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewImgMapper reviewImgMapper;

    @Override
    public void registReview(ReviewRegistDTO reviewRegistDTO) {

        log.info("===== Start RegistReview Service =====");

        // gno 추출
        Long gno = reviewRegistDTO.getGno();

        // gno가 0일 시 댓글(리뷰)
        if (gno == 0) {

            reviewMapper.registReview(reviewRegistDTO);

            // 리뷰번호 추출
            Long rno = reviewRegistDTO.getRno();

            // gno(그룹번호) 업데이트
            reviewMapper.updateGno(rno);

        }
        // gno가 0이 아닐 시 리뷰에 대한 답글
        else {
            reviewMapper.registChildReview(reviewRegistDTO);
        }
        Long rno = reviewRegistDTO.getRno();

        // 이미지 업로드 후 프론트 측에서 useState에 imgsName을 보관하고 있을 것
        // registProductDTO에서 imgsName을 뽑아온다.
        List<String> imgsName = reviewRegistDTO.getImgsName();

        if (imgsName != null && !imgsName.isEmpty()) {

            AtomicInteger index = new AtomicInteger();

            // List형식(이미지가 여러개)으로 imgName, UUID, pno, ord등 을 Map 형식(key : value)로 받아온다.
            List<Map<String, String>> imgList = imgsName.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String imgName = str.substring(37);

                return Map.of("uuid", uuid, "imgName", imgName, "rno", "" + rno, "imgOrd",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(imgList);

            // 이미지 등록
            reviewImgMapper.registReviewImg(imgList);

            log.info("===== End RegistReview Service =====");

        }

    }

    // 리뷰 상세보기 서비스
    @Override
    public ReviewReadDTO readReview(Long rno) {

        log.info("===== Start ReadReview Service =====");

        return reviewMapper.readReview(rno);

    }

    // 리뷰 리스트 서비스
    @Override
    public PageResponseDTO<ReviewListDTO> getReviewList(PageRequestDTO pageRequestDTO, String memberID) {

        log.info("===== Start ReviewList Service =====");

        List<ReviewListDTO> list = reviewMapper.getReviewList(pageRequestDTO, memberID);
        Long total = reviewMapper.getTotal(pageRequestDTO, memberID);

        return PageResponseDTO.<ReviewListDTO>withAll()
                    .list(list)
                    .total(total)
                    .pageRequestDTO(pageRequestDTO)
                    .build();

    }

    /* 리뷰 삭제 */
    @Override
    public void removeReview(Long rno) {

        log.info("===== Start RemoveReview Service =====");

        reviewMapper.removeReview(rno);
        reviewImgMapper.removeReviewImg(rno);

    }

}
