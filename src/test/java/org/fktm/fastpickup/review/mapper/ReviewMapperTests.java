package org.fktm.fastpickup.review.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.fktm.fastpickup.review.dto.ReviewListDTO;
import org.fktm.fastpickup.review.dto.ReviewModifyDTO;
import org.fktm.fastpickup.review.dto.ReviewReadDTO;
import org.fktm.fastpickup.review.dto.ReviewRegistDTO;
import org.fktm.fastpickup.review.mappers.ReviewImgMapper;
import org.fktm.fastpickup.review.mappers.ReviewMapper;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReviewMapperTests {

    @Autowired(required = false)
    private ReviewImgMapper reviewImgMapper;

    @Autowired(required = false)
    private ReviewMapper reviewMapper;

    private static final Long TEST_RNO = 13L;
    private static final Long TEST_PNO = 32L;
    private static final String TEST_MEMBERID = "wndyd0110@naver.com";
    private static final Long TEST_GNO = 0L;
    private static final String TEST_REVIEW_TITLE = "정말 맛있어요";
    private static final String TEST_REVIEW_CONTENT = "진짜 맛있어요 강추!!! 좋은 음식 감사합니다 사장님~~";

    private static final String TEST_IMG_UUID = "49c48e7d-e85a-4704-b47a-942e240ef675";
    private static final String TEST_IMG_UUID2 = "48c48e7d-e85a-4704-b47a-942e240ef675";

    private static final String TEST_MODIFY_TITLE = "수정된 제목";
    private static final String TEST_MODIFY_CONTENT = "수정된 내용";

    private static final String TEST_MODIFY_IMG_UUID = "Modify7d-e85a-4704-b47a-942e240ef675";
    private static final String TEST_MODIFY_IMG_UUID2 = "Modify8d-e85a-4704-b47a-942e240ef675";

    private static final String TEST_IMG_NAME = "postMan.jpg";
    private static final String TEST_IMG_NAME2 = "postMan2.jpg";

    private ReviewRegistDTO reviewRegistDTO;
    private ReviewReadDTO reviewReadDTO;
    private ReviewModifyDTO reviewModifyDTO;
    private PageRequestDTO pageRequestDTO;

    // 이미지
    private List<String> imgsNameList = new ArrayList<>();
    private List<String> modifyImgsNameList = new ArrayList<>();

    @BeforeEach
    public void init() {

        imgsNameList.add(TEST_IMG_UUID + "_" + TEST_IMG_NAME);
        imgsNameList.add(TEST_IMG_UUID2 + "_" + TEST_IMG_NAME2);

        modifyImgsNameList.add(TEST_MODIFY_IMG_UUID + "_" + TEST_IMG_NAME);
        modifyImgsNameList.add(TEST_MODIFY_IMG_UUID2 + "_" + TEST_IMG_NAME2);

        reviewRegistDTO = ReviewRegistDTO.builder()
                .memberID(TEST_MEMBERID)
                .pno(TEST_PNO)
                .gno(TEST_GNO)
                .reviewTitle(TEST_REVIEW_TITLE)
                .reviewContent(TEST_REVIEW_CONTENT)
                .imgsName(imgsNameList)
                .build();

        reviewModifyDTO = ReviewModifyDTO.builder()
                .rno(TEST_RNO)
                .reviewTitle(TEST_MODIFY_TITLE)
                .reviewContent(TEST_MODIFY_CONTENT)
                .imgsName(modifyImgsNameList)
                .build();

        pageRequestDTO = PageRequestDTO.builder().build();
    }

    @DisplayName("리뷰 등록 매퍼 테스트")
    @Transactional
    @Test
    public void registProduct() {

        // GIVEN
        log.info("===== Start Regist Review Mapper Test=====");

        Long gno = reviewRegistDTO.getGno();

        if (gno == 0L) {
            reviewMapper.registReview(reviewRegistDTO);

            // rno 리뷰번호 추출
            Long rno = reviewRegistDTO.getRno();

            log.info("==== rno ====");
            log.info(rno);

            // gno(그룹번호)를 rno로 업데이트
            reviewMapper.updateGno(rno);
        } else {
            // gno가 0이 아닌 상위 rno일시 리뷰 하위댓글
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

        }

    }

    @DisplayName("리뷰 상세보기 매퍼 테스트")
    @Transactional
    @Test
    public void readReview() {

        // GIVEN
        log.info("===== Start Read Review Mapper Test=====");

        // WEHN
        reviewReadDTO = reviewMapper.readReview(TEST_RNO);
        log.info(reviewReadDTO);

        // THEN
    }

    @DisplayName("마이페이지 리뷰 리스트 매퍼 테스트")
    @Transactional
    @Test
    public void getReviewList() {

        // GIVEN
        log.info("===== Start ReviewList Mapper Test=====");

        // WEHN
        List<ReviewListDTO> list = reviewMapper.getReviewList(pageRequestDTO, TEST_MEMBERID);
        log.info(list);

    }

    @DisplayName("상품상세페이지 리뷰 리스트 매퍼 테스트")
    @Transactional
    @Test
    public void getProductReviewList() {

        // GIVEN
        log.info("===== Start ProductReviewList Mapper Test=====");

        // WEHN
        List<ReviewListDTO> list = reviewMapper.getProductReviewList(pageRequestDTO, TEST_PNO);
        log.info(list);

    }

    @DisplayName("리뷰 삭제 매퍼 테스트")
    @Transactional
    @Test
    public void RemoveReview() {

        // GIVEN
        log.info("===== Start RemoveReview Mapper Test=====");

        // WHEN
        reviewMapper.removeReview(TEST_RNO);

    }

    @DisplayName("리뷰 수정 매퍼 테스트")
    @Transactional
    @Test
    public void ModifyReview() {

        // GIVEN
        log.info("===== Start ModifyReview Mapper Test=====");

        // WHEN
        reviewMapper.modifyReview(reviewModifyDTO);

        // rno 추출
        Long rno = reviewModifyDTO.getRno();
        // 기존 이미지 삭제
        reviewImgMapper.removeReviewImg(rno);

        List<String> imgsName = reviewModifyDTO.getImgsName();

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

        }

    }

}