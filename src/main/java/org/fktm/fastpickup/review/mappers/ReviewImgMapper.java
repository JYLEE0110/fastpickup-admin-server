package org.fktm.fastpickup.review.mappers;

import java.util.List;
import java.util.Map;

public interface ReviewImgMapper {

        // 이미지 등록 매퍼
    // 인자 값은 상품 등록 시 List형식(이미지가 여러개)으로 imgName, UUID, rno, ord등 을 Map 형식(key : value)로 받아온다. 
    int registReviewImg(List<Map<String, String>> imgList);

    // int removeImg()
    int removeReviewImg(Long rno);
    
}
