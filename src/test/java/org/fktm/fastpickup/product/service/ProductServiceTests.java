package org.fktm.fastpickup.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductModifyDTO;
import org.fktm.fastpickup.product.dto.ProductReadDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

    @Autowired(required = false)
    private ProductService productService;

    private static final Long TEST_PNO = 29L;
    private static final int TEST_CNO = 1;
    private static final int TEST_PRODUCT_PRICE = 1000;
    private static final String TEST_PRODUCT_NAME = "핫 라떼";
    private static final String TEST_PRODUCT_CONTENT = "맛있고 용암같이 뜨거운 라떼";
    private static final String TEST_IMG_UUID = "52c48e7d-e85a-4704-b47a-942e240ef675";
    private static final String TEST_IMG_UUID2 = "53c48e7d-e85a-4704-b47a-942e240ef675";

    private static final String TEST_MODIFY_IMG_UUID = "Modify7d-e85a-4704-b47a-942e240ef675";
    private static final String TEST_MODIFY_IMG_UUID2 = "Modify8d-e85a-4704-b47a-942e240ef675";

    private static final String TEST_IMG_NAME = "postMan.jpg";
    private static final String TEST_IMG_NAME2 = "postMan2.jpg";
    private static final String TEST_PRODUCT_TYPE = "p";
    private static final String TEST_PRODUCT_KEYWORD = null;

    private ProductRegistDTO productRegistDTO;
    private ProductReadDTO productReadDTO;
    private ProductModifyDTO productModifyDTO;
    private List<ProductListDTO> productList;

    // 이미지
    private List<String> imgsNameList = new ArrayList<>();
    private List<String> modifyimgsNameList = new ArrayList<>();

    private PageRequestDTO pageRequestDTO;

    @BeforeEach
    public void init() {

        imgsNameList.add(TEST_IMG_UUID + "_" + TEST_IMG_NAME);
        imgsNameList.add(TEST_IMG_UUID2 + "_" + TEST_IMG_NAME2);

        modifyimgsNameList.add(TEST_MODIFY_IMG_UUID + "_" + TEST_IMG_NAME);
        modifyimgsNameList.add(TEST_MODIFY_IMG_UUID2 + "_" + TEST_IMG_NAME);

        productRegistDTO = ProductRegistDTO.builder()
                .cno(TEST_CNO)
                .productName(TEST_PRODUCT_NAME)
                .productPrice(TEST_PRODUCT_PRICE)
                .productContent(TEST_PRODUCT_CONTENT)
                .imgsName(imgsNameList)
                .build();

        productModifyDTO = ProductModifyDTO.builder()
                .pno(TEST_PNO)
                .productName("수정 된 상품 이름")
                .productPrice(999999)
                .productContent("수정 된 상품 내용")
                .imgsName(modifyimgsNameList)
                .build();

        pageRequestDTO = PageRequestDTO.builder()
                .type(TEST_PRODUCT_TYPE)
                .keyword(TEST_PRODUCT_KEYWORD)
                .build();

    }

    // 상품 등록 서비스 
    @DisplayName("상품 등록 서비스 테스트")
    @Test
    @Transactional
    public void registProduct() {

        // GIVEN
        log.info("===== Start Regist Product Service TEST =====");

        // WHEN
        Long pno = productService.registProduct(productRegistDTO);
        productReadDTO = productService.readProduct(pno);

        //THEN
        Assertions.assertNotNull(productReadDTO , "데이터 입력이 잘못 되었습니다.");
        log.info("===== End Regist Product Service TEST =====");

    }

    // 상품 상세 서비스 
    @DisplayName("상품 상세 서비스 테스트")
    @Test
    @Transactional
    public void readProduct() {

        // GIVEN
        log.info("===== Start Read Product Service Test =====");

        // WHEN
        productReadDTO = productService.readProduct(TEST_PNO);

        //THEN
        Assertions.assertNotNull(productReadDTO , "데이터가 없습니다.");
        log.info("===== End Read Product Service Test =====");

    }

    // 상품 리스트 & 페이징 데이터 서비스 
    @DisplayName("상품 리스트 & 페이징 데이터 서비스 테스트")
    @Test
    @Transactional
    public void getProductList() {

        // GIVEN
        log.info("===== Start Get ProductList Service Test =====");

        // WHEN
        PageResponseDTO<ProductListDTO> list = productService.getProductList(pageRequestDTO);
        log.info(list);

        //THEN
        Assertions.assertNotNull(list, "데이터가 없습니다.");
        log.info("===== End Get ProductList Service Test =====");

    }

    // 상품 삭제 서비스 
    @DisplayName("상품 삭제 서비스 테스트")
    @Test
    @Transactional
    public void removeProduct() {

        // GIVEN
        log.info("===== Start Remove Product Service Test =====");

        // WHEN
        productService.removeProduct(TEST_PNO);
        productReadDTO = productService.readProduct(TEST_PNO);
        
        //THEN
        Assertions.assertNull(productReadDTO, "데이터 삭제가 정상적으로 되지 않았습니다.");
        log.info("===== End Remove Product Service Test =====");

    }

    // 상품 수정 서비스 
    @DisplayName("상품 수정 서비스 테스트")
    @Test
    @Transactional
    public void modifyProduct() {

        // GIVEN
        log.info("===== Start Modify Product Service Test =====");

        // WHEN
        // 수정 전 ProductName
        String beforeName = productService.readProduct(TEST_PNO).getProductName();
        productService.modifyProduct(productModifyDTO);
        Long pno = productModifyDTO.getPno();
        
        // 수정 후 ProductName
        String afterName = productService.readProduct(pno).getProductName();

        //THEN
        // 수정 전 과 수정 후 ProductName을 비교한다.
        Assertions.assertNotEquals(beforeName, afterName, "데이터 수정이 정상적으로 되지 않았습니다.");
        log.info("===== End Modify Product Service Test =====");

    }

}
