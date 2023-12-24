package org.fktm.fastpickup.product.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.fktm.fastpickup.exception.customexception.FastPickUpException;
import org.fktm.fastpickup.exception.enumcode.ExceptionCode;
import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductModifyDTO;
import org.fktm.fastpickup.product.dto.ProductReadDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.product.exception.enumImpl.ProductExceptionCode;
import org.fktm.fastpickup.product.mappers.ProductImgMapper;
import org.fktm.fastpickup.product.mappers.ProductMapper;
import org.fktm.fastpickup.product.service.ProductService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    // DI 의존성 주입 => final을 붙이면 RequireArgsConstructor
    private final ProductMapper productMapper;
    private final ProductImgMapper productImgMapper;

    // 상품 등록 서비스
    @Override
    public Long registProduct(ProductRegistDTO productRegistDTO) {

        log.info("===== registProduct Service =====");

        // ProductName이 입력되지 않았을 시 예외처리
        if(productRegistDTO.getProductName() == null || productRegistDTO.getProductName().isEmpty()){
            throw new FastPickUpException(ProductExceptionCode.NULL_PRODUCT_NAME);
        }

        // Mapper로 등록 후 selectKey로 pno 값 반환
        productMapper.registProduct(productRegistDTO);

        // selectKey를 사용 => registDTO에 pno를 가져온다.
        Long pno = productRegistDTO.getPno();

        // productRegistDTO에서 이미지들을 가지고온다.
        List<String> productImgsList = productRegistDTO.getImgsName();

        if (productImgsList != null && !productImgsList.isEmpty()) {

            AtomicInteger index = new AtomicInteger();

            // List형식(이미지가 여러개)으로 imgName, UUID, pno, ord등 을 Map 형식(key : value)로 받아온다.
            List<Map<String, String>> productImgList = productImgsList.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String imgName = str.substring(37);

                return Map.of("uuid", uuid, "imgName", imgName, "pno", "" + pno, "imgOrd",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(productImgList);

            // 이미지 등록
            productImgMapper.registProductImg(productImgList);
        }

        return pno;

    }

    // 상품 상세 서비스
    @Override
    public ProductReadDTO readProduct(Long pno) {

        ProductReadDTO productReadDTO = productMapper.readProduct(pno);

        log.info("===== readProduct Service =====");
        log.info(productReadDTO);

        return productReadDTO;
    }

    // 상품 리스트 & 페이징 정보 서비스
    @Override
    public PageResponseDTO<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO) {

        log.info("===== getProductList Service =====");

        // 상품 리스트를 가져온다.
        List<ProductListDTO> productList = productMapper.getProductList(pageRequestDTO);

        // 현재 페이지의 총 데이터 개수 (1~10 page = 101 / 11 ~ 20 = 201)
        // 만약 데이터가 249개에 현재 페이지가 21 ~ 24면 301이 아닌 249 반환
        Long total = productMapper.getTotal(pageRequestDTO);

        // builder로 데이터 반환
        return PageResponseDTO.<ProductListDTO>withAll()
                            .list(productList)
                            .total(total)
                            .pageRequestDTO(pageRequestDTO)
                            .build();

    }

    // 상품 삭제 서비스
    @Override
    public void removeProduct(Long pno) {

        log.info("===== removeProduct Service =====");

        // 상품 삭제
        productMapper.removeProduct(pno);

        // 상품 이미지 삭제
        productImgMapper.removeProductImg(pno);


    }

    // 상품 수정 서비스
    @Override
    public void modifyProduct(ProductModifyDTO productModifyDTO) {

        log.info("===== modifyProduct Service =====");

        // 상품 수정 => tbl_product
        productMapper.modifyProduct(productModifyDTO);
        
        // 상품 번호 추출
        Long pno = productModifyDTO.getPno();
 

        // 상품 이미지 삭제
        productImgMapper.removeProductImg(pno);
        
        // 수정될 상품 이미지를 DTO에서 추출
        List<String> productImgsList = productModifyDTO.getImgsName();

        if (productImgsList != null && !productImgsList.isEmpty()) {

            AtomicInteger index = new AtomicInteger();

            // List형식(이미지가 여러개)으로 imgName, UUID, pno, ord등 을 Map 형식(key : value)로 받아온다.
            List<Map<String, String>> productImgList = productImgsList.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String imgName = str.substring(37);

                return Map.of("uuid", uuid, "imgName", imgName, "pno", "" + pno, "imgOrd",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(productImgList);

            // 이미지 등록
            productImgMapper.registProductImg(productImgList);
        }
    }
}
